package com.rowland.auction.presentation.bidfeature.repository;

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

    private final BidDataStoreFactory bidDataStoreFactory;
    private final BidPayloadDataMapper bidEntityDataMapper;

    /**
     * Constructs a {@link IBidRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param bidEntityDataMapper {@link BidPayloadDataMapper}.
     */
    @Inject
    public BidDataRepository(BidDataStoreFactory dataStoreFactory, BidPayloadDataMapper bidEntityDataMapper) {
        this.bidDataStoreFactory = dataStoreFactory;
        this.bidEntityDataMapper = bidEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<Bid>> getList() {
        //we always get all bids from the cloud
        final IBidDataStore bidDataStore = this.bidDataStoreFactory.createCloudDataStore();
        return bidDataStore.bidEntityList().map(bidEntities -> this.bidEntityDataMapper.transform(bidEntities));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<Bid> getItem(int bidId) {
        final IBidDataStore bidDataStore = this.bidDataStoreFactory.create(bidId);
        return bidDataStore.bidEntityDetails(bidId).map(bidEntity -> this.bidEntityDataMapper.transform(bidEntity));
    }
}
