package com.rowland.auction.presentation.bidfeature.repository;

import android.util.Log;

import com.rowland.auction.data.bidfeature.payload.mapper.BidPayloadDataMapper;
import com.rowland.auction.data.bidfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.domain.repository.IRepository;
import com.rowland.auction.domain.bidfeature.model.Bid;
import com.rowland.auction.domain.bidfeature.repository.IBidRepository;
import com.rowland.auction.presentation.bidfeature.repository.datasource.BidDataStoreFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link IBidRepository} for retrieving bid data.
 */
@Singleton
public class BidDataRepository implements IRepository, IBidRepository {

    // Class log identifier
    public final static String LOG_TAG = BidDataRepository.class.getSimpleName();

    private final BidDataStoreFactory bidDataStoreFactory;
    private final BidPayloadDataMapper bidPayloadDataMapper;

    /**
     * Constructs a {@link IBidRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param bidPayloadDataMapper {@link BidPayloadDataMapper}.
     */
    @Inject
    public BidDataRepository(BidDataStoreFactory dataStoreFactory, BidPayloadDataMapper bidPayloadDataMapper) {
        this.bidDataStoreFactory = dataStoreFactory;
        this.bidPayloadDataMapper = bidPayloadDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<Bid>> getList() {
        //we always get all bids from the cloud
        final IBidDataStore bidDataStore = this.bidDataStoreFactory.createCloudDataStore();
        Log.d(LOG_TAG, "I WAS CALLED by getList");
        return bidDataStore.bidPayloadList().map(bidPayload -> this.bidPayloadDataMapper.transform(bidPayload));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<Bid> getItem(int bidId) {
        final IBidDataStore bidDataStore = this.bidDataStoreFactory.create(bidId);
        Log.d(LOG_TAG, "I WAS CALLED by getItem");
        return bidDataStore.bidPayloadDetails(bidId).map(bidEntity -> this.bidPayloadDataMapper.transform(bidEntity));
    }
}
