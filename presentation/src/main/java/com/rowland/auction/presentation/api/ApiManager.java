package com.rowland.auction.presentation.api;

import android.content.Context;

import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.data.userfeature.payload.UserPayload;

import java.util.List;

import rx.Observable;

public class ApiManager extends ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    public ApiManager(Context context) {
        mContext = context;
    }

    /**
     * Auth API
     */
    public Observable<String> login(String username, String password) {
        return getAuthApi().login(username, password);
    }

    public Observable<UserPayload> register(UserPayload payload) {
        return getAuthApi().register(payload);
    }

    /**
     * User API
     */
    public Observable<List<UserPayload>> listUsers() {
        return getUsersApi().listUsers();
    }

    public Observable<UserPayload> getUserById(int id) {
        return getUsersApi().getUserWithId(id);
    }

    /**
     * Bid API
     */
    public Observable<List<BidPayload>> listBids() {
        return getBidsApi().listBids();
    }

    public Observable<BidPayload> getBidById(int id) {
        return getBidsApi().getBidWithId(id);
    }
}
