package com.rowland.auction.presentation.userfeature.repository.datasource;

import com.rowland.auction.data.userfeature.cache.IBidCache;
import com.rowland.auction.data.userfeature.payload.BidPayload;
import com.rowland.auction.data.userfeature.repository.datasource.IBidDataStore;

import java.util.List;

import rx.Observable;

/**
 * {@link IBidDataStore} implementation based on file system data store.
 */
public class DiskUserDataStore implements IBidDataStore {

    private final IBidCache userCache;

    /**
     * Construct a {@link IBidDataStore} based file system data store.
     *
     * @param userCache A {@link IBidCache} to cache data retrieved from the api.
     */
    public DiskUserDataStore(IBidCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<List<BidPayload>> userEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<BidPayload> userEntityDetails(final int userId) {
        return this.userCache.get(userId);
    }
}
