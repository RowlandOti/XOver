package com.rowland.auction.presentation.bidfeature.repository.datasource;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.rowland.auction.data.exception.NetworkConnectionException;
import com.rowland.auction.data.bidfeature.cache.IBidCache;
import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.data.bidfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.data.utility.NetworkUtility;
import com.rowland.auction.presentation.api.ApiManager;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IBidDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudBidDataStore implements IBidDataStore {

    //private final IRestApi restApi;
    private final ApiManager mApiManager;
    private final IBidCache bidCache;
    private final Action1<BidPayload> saveToCacheAction = bidEntity -> {
        if (bidEntity != null) {
            CloudBidDataStore.this.bidCache.put(bidEntity);
        }
    };

    /**
     * Construct a {@link IBidDataStore} based on connections to the api (Cloud).
     *
     * @param apiManager The {@link ApiManager} implementation to use.
     * @param bidCache  A {@link IBidCache} to cache data retrieved from the api.
     */
    public CloudBidDataStore(ApiManager apiManager, IBidCache bidCache) {
        this.mApiManager = apiManager;
        this.bidCache = bidCache;
    }

    @RxLogObservable
    @Override
    public Observable<List<BidPayload>> bidEntityList() {
        Observable<List<BidPayload>> bidListPayloadObservable = this.mApiManager.listBids();
        return bidListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    List<BidPayload> responseBidEntities = bidListPayloadObservable.toBlocking().single();
                    if (responseBidEntities != null) {
                        subscriber.onNext(responseBidEntities);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<BidPayload> bidEntityDetails(final int bidId) {
        Observable<BidPayload> bidDetailsPayloadObservable = this.mApiManager.getBidById(bidId).doOnNext(saveToCacheAction);
        return bidDetailsPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    BidPayload responseBidDetails = bidDetailsPayloadObservable.toBlocking().single();
                    if (responseBidDetails != null) {
                        subscriber.onNext(responseBidDetails);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }
}
