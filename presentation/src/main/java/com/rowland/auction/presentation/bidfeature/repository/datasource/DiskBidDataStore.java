package com.rowland.auction.presentation.bidfeature.repository.datasource;

import com.rowland.auction.data.bidfeature.cache.IBidCache;
import com.rowland.auction.data.bidfeature.exception.BidNotFoundException;
import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.data.bidfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.presentation.bidfeature.mapper.BidPayloadModelMapper;
import com.rowland.auction.presentation.bidfeature.model.BidModel;

import java.util.List;

import rx.Observable;

/**
 * {@link IBidDataStore} implementation based on file system data store.
 */
public class DiskBidDataStore implements IBidDataStore {

    private final IBidCache bidCache;
    private final BidPayloadModelMapper bitPayloadModelMapper;

    /**
     * Construct a {@link IBidDataStore} based file system data store.
     *
     * @param bidCache A {@link IBidCache} to cache data retrieved from the api.
     */
    public DiskBidDataStore(IBidCache bidCache) {
        this.bidCache = bidCache;
        this.bitPayloadModelMapper = new BidPayloadModelMapper();
    }

    public Observable<List<BidPayload>> bidPayloadList() {
        return Observable.create(subscriber -> {
            List<BidPayload> bidPayloads = bitPayloadModelMapper.transformModelToPayload(BidModel.listAll(BidModel.class));
            if (bidPayloads != null) {
                subscriber.onNext(bidPayloads);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new BidNotFoundException());
            }
        });
    }

  /*  @Override
    public Observable<BidPayload> bidPayloadDetails(final int bidId) {
        return this.bidCache.get(bidId);
    }*/

    @Override
    public Observable<BidPayload> bidPayloadDetails(final int bidId) {
        return Observable.create(subscriber -> {
            BidPayload bidPayloads = bitPayloadModelMapper.transformModelToPayload(BidModel.find(BidModel.class, "bidModelId = ?", new String[] {Integer.toString(bidId)}).get(0));
            if (bidPayloads != null) {
                subscriber.onNext(bidPayloads);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new BidNotFoundException());
            }
        });
    }

}
