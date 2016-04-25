package com.rowland.auction.presentation.userfeature.repository.datasource;

import android.content.Context;

import com.rowland.auction.data.userfeature.cache.IBidCache;
import com.rowland.auction.data.userfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.api.ApiManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IBidDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

    private final Context context;
    private final IBidCache userCache;

    @Inject
    public UserDataStoreFactory(Context context, IBidCache userCache) {
        if (context == null || userCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }

    /**
     * Create {@link IBidDataStore} from a user id.
     */
    public IBidDataStore create(int userId) {
        IBidDataStore userDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
            userDataStore = new DiskUserDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }
        return userDataStore;
    }

    /**
     * Create {@link IBidDataStore} to retrieve data from the Cloud.
     */
    public IBidDataStore createCloudDataStore() {
        ApiManager apiManager = ApplicationController.apiManager;
        return new CloudUserDataStore(apiManager, this.userCache);
    }
}
