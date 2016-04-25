package com.rowland.auction.presentation.bidfeature.repository.datasource;

import com.rowland.auction.data.bidfeature.cache.IBidCache;
import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.data.bidfeature.repository.datasource.IBidDataStore;

import java.util.List;

import rx.Observable;

/**
 * {@link IBidDataStore} implementation based on file system data store.
 */
public class DiskBidDataStore implements IBidDataStore {

    private final IBidCache bidCache;

    /**
     * Construct a {@link IBidDataStore} based file system data store.
     *
     * @param bidCache A {@link IBidCache} to cache data retrieved from the api.
     */
    public DiskBidDataStore(IBidCache bidCache) {
        this.bidCache = bidCache;
    }

    @Override
    public Observable<List<BidPayload>> bidEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of bids.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<BidPayload> bidEntityDetails(final int bidId) {
        return this.bidCache.get(bidId);
    }
}
