package com.rowland.auction.presentation.bidfeature.repository.datasource;

import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.rowland.auction.data.bidfeature.cache.IBidCache;
import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.data.bidfeature.repository.datasource.IBidDataStore;
import com.rowland.auction.data.exception.NetworkConnectionException;
import com.rowland.auction.data.utility.NetworkUtility;
import com.rowland.auction.presentation.api.ApiManager;
import com.rowland.auction.presentation.bidfeature.mapper.BidPayloadModelMapper;
import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.bidfeature.repository.FakeBidSeeder;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IBidDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudBidDataStore implements IBidDataStore {

    // Class log identifier
    public final static String LOG_TAG = CloudBidDataStore.class.getSimpleName();

    //private final IRestApi restApi;
    private final ApiManager mApiManager;
    private final IBidCache bidCache;
    private final BidPayloadModelMapper bitPayloadModelMapper;
    private final Action1<BidPayload> saveToCacheAction = bidEntity -> {
        if (bidEntity != null) {
            CloudBidDataStore.this.bidCache.put(bidEntity);
        }
    };

    /**
     * Construct a {@link IBidDataStore} based on connections to the api (Cloud).
     *
     * @param apiManager The {@link ApiManager} implementation to use.
     * @param bidCache   A {@link IBidCache} to cache data retrieved from the api.
     */
    public CloudBidDataStore(ApiManager apiManager, IBidCache bidCache) {
        this.mApiManager = apiManager;
        this.bidCache = bidCache;
        this.bitPayloadModelMapper = new BidPayloadModelMapper();
    }

    @RxLogObservable
    @Override
    public Observable<List<BidPayload>> bidPayloadList() {
        Observable<List<BidPayload>> bidListPayloadObservable = this.mApiManager.listBids();
        return bidListPayloadObservable.create(subscriber -> {
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                List<BidPayload> responseBidPayload = null;
                try {
                    responseBidPayload = bidListPayloadObservable.toBlocking().single();
                } catch (Exception e) {
                    // subscriber.onError(new NetworkConnectionException(e.getCause()));
                    Log.d(LOG_TAG, "NETWORK UNKNOWNHOST EXCEPTION 1");
                }
                if (responseBidPayload != null) {
                    subscriber.onNext(responseBidPayload);
                    for (BidPayload bidPayload : responseBidPayload) {
                        BidModel bidModel = bitPayloadModelMapper.transformPayloadToModel(bidPayload);
                        bidModel.save();
                        Log.d(LOG_TAG, "SAVING FROM NETWORK");
                    }
                    subscriber.onCompleted();
                } else {
                    responseBidPayload = bitPayloadModelMapper.transformModelToPayload(FakeBidSeeder.seedDatabase());
                    subscriber.onNext(responseBidPayload);
                    Log.d(LOG_TAG, "SAVING FROM FAKING");
                    subscriber.onCompleted();
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
                Log.d(LOG_TAG, "NETWORK INTERNET EXCEPTION");
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<BidPayload> bidPayloadDetails(final int bidId) {
        Observable<BidPayload> bidDetailsPayloadObservable = this.mApiManager.getBidById(bidId).doOnNext(saveToCacheAction);
        return bidDetailsPayloadObservable.create(subscriber -> {
            BidPayload responseBidDetails = null;
            if (NetworkUtility.isNetworkAvailable(mApiManager.getContext())) {
                try {
                    responseBidDetails = bidDetailsPayloadObservable.toBlocking().single();
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
                if (responseBidDetails != null) {
                    subscriber.onNext(responseBidDetails);
                    subscriber.onCompleted();
                } else {
                    // ToDo: Fake some Data
                }

            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }
}
