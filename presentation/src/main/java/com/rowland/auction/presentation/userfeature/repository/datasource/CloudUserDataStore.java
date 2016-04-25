package com.rowland.auction.presentation.userfeature.repository.datasource;

import com.rowland.auction.data.exception.NetworkConnectionException;
import com.rowland.auction.data.userfeature.cache.IBidCache;
import com.rowland.auction.data.userfeature.payload.BidPayload;
import com.rowland.auction.data.userfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.data.utility.NetworkUtility;
import com.rowland.auction.presentation.api.ApiManager;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IBidDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements IBidDataStore {

    //private final IRestApi restApi;
    private final ApiManager mApiManager;
    private final IBidCache userCache;
    private final Action1<BidPayload> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    /**
     * Construct a {@link IBidDataStore} based on connections to the api (Cloud).
     *
     * @param apiManager The {@link ApiManager} implementation to use.
     * @param userCache  A {@link IBidCache} to cache data retrieved from the api.
     */
    public CloudUserDataStore(ApiManager apiManager, IBidCache userCache) {
        this.mApiManager = apiManager;
        this.userCache = userCache;
    }

    @RxLogObservable
    @Override
    public Observable<List<BidPayload>> userEntityList() {
        Observable<List<BidPayload>> userListPayloadObservable = this.mApiManager.listUsers();
        return userListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    List<BidPayload> responseUserEntities = userListPayloadObservable.toBlocking().single();
                    if (responseUserEntities != null) {
                        subscriber.onNext(responseUserEntities);
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
    public Observable<BidPayload> userEntityDetails(final int userId) {
        Observable<BidPayload> userDetailsPayloadObservable = this.mApiManager.getUserById(userId).doOnNext(saveToCacheAction);
        return userDetailsPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    BidPayload responseUserDetails = userDetailsPayloadObservable.toBlocking().single();
                    if (responseUserDetails != null) {
                        subscriber.onNext(responseUserDetails);
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
