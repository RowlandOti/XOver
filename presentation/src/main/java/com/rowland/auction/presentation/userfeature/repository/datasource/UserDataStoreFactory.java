package com.rowland.auction.presentation.userfeature.repository.datasource;

import android.content.Context;

import com.rowland.auction.data.userfeature.cache.IUserCache;
import com.rowland.auction.data.userfeature.repository.datasource.IUserDataStore;
import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.api.ApiManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IUserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

    private final Context context;
    private final IUserCache userCache;

    @Inject
    public UserDataStoreFactory(Context context, IUserCache userCache) {
        if (context == null || userCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userCache = userCache;
    }

    /**
     * Create {@link IUserDataStore} from a user id.
     */
    public IUserDataStore create(int userId) {
        IUserDataStore userDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
            userDataStore = new DiskUserDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }
        return userDataStore;
    }

    /**
     * Create {@link IUserDataStore} to retrieve data from the Cloud.
     */
    public IUserDataStore createCloudDataStore() {
        ApiManager apiManager = ApplicationController.apiManager;
        return new CloudUserDataStore(apiManager, this.userCache);
    }
}
