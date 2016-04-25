package com.rowland.auction.presentation.bidfeature.repository.datasource;

import android.content.Context;

import com.rowland.auction.data.bidfeature.cache.IBidCache;
import com.rowland.auction.data.bidfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.api.ApiManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IBidDataStore}.
 */
@Singleton
public class BidDataStoreFactory {

    private final Context context;
    private final IBidCache bidCache;

    @Inject
    public BidDataStoreFactory(Context context, IBidCache bidCache) {
        if (context == null || bidCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.bidCache = bidCache;
    }

    /**
     * Create {@link IBidDataStore} from a bid id.
     */
    public IBidDataStore create(int bidId) {
        IBidDataStore bidDataStore;

        if (!this.bidCache.isExpired() && this.bidCache.isCached(bidId)) {
            bidDataStore = new DiskBidDataStore(this.bidCache);
        } else {
            bidDataStore = createCloudDataStore();
        }
        return bidDataStore;
    }

    /**
     * Create {@link IBidDataStore} to retrieve data from the Cloud.
     */
    public IBidDataStore createCloudDataStore() {
        ApiManager apiManager = ApplicationController.apiManager;
        return new CloudBidDataStore(apiManager, this.bidCache);
    }
}
