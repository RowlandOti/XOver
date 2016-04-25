package com.rowland.auction.presentation.userfeature.repository.datasource;

import com.rowland.auction.data.userfeature.cache.IUserCache;
import com.rowland.auction.data.userfeature.payload.UserPayload;
import com.rowland.auction.data.userfeature.repository.datasource.IUserDataStore;

import java.util.List;

import rx.Observable;

/**
 * {@link IUserDataStore} implementation based on file system data store.
 */
public class DiskUserDataStore implements IUserDataStore {

    private final IUserCache userCache;

    /**
     * Construct a {@link IUserDataStore} based file system data store.
     *
     * @param userCache A {@link IUserCache} to cache data retrieved from the api.
     */
    public DiskUserDataStore(IUserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<List<UserPayload>> userEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<UserPayload> userEntityDetails(final int userId) {
        return this.userCache.get(userId);
    }
}
