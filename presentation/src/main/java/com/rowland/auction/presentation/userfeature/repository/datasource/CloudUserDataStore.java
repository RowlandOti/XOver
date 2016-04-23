package com.rowland.auction.presentation.userfeature.repository.datasource;

import com.rowland.auction.data.exception.NetworkConnectionException;
import com.rowland.auction.data.userfeature.cache.IUserCache;
import com.rowland.auction.data.userfeature.payload.UserPayload;
import com.rowland.auction.data.userfeature.repository.datasource.IUserDataStore;
import com.rowland.auction.data.utility.NetworkUtility;
import com.rowland.auction.presentation.api.ApiManager;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IUserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements IUserDataStore {

    //private final IRestApi restApi;
    private final ApiManager mApiManager;
    private final IUserCache userCache;
    private final Action1<UserPayload> saveToCacheAction = userEntity -> {
        if (userEntity != null) {
            CloudUserDataStore.this.userCache.put(userEntity);
        }
    };

    /**
     * Construct a {@link IUserDataStore} based on connections to the api (Cloud).
     *
     * @param apiManager The {@link ApiManager} implementation to use.
     * @param userCache  A {@link IUserCache} to cache data retrieved from the api.
     */
    public CloudUserDataStore(ApiManager apiManager, IUserCache userCache) {
        this.mApiManager = apiManager;
        this.userCache = userCache;
    }

    @RxLogObservable
    @Override
    public Observable<List<UserPayload>> userEntityList() {
        Observable<List<UserPayload>> userListPayloadObservable = this.mApiManager.listUsers();
        return userListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    List<UserPayload> responseUserEntities = userListPayloadObservable.toBlocking().single();
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
    public Observable<UserPayload> userEntityDetails(final int userId) {
        Observable<UserPayload> userDetailsPayloadObservable = this.mApiManager.getUserById(userId).doOnNext(saveToCacheAction);
        return userDetailsPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    UserPayload responseUserDetails = userDetailsPayloadObservable.toBlocking().single();
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
