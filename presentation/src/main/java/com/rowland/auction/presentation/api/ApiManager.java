package com.rowland.auction.presentation.api;

import android.content.Context;

import com.rowland.auction.data.userfeature.payload.BidPayload;

import java.util.List;

import rx.Observable;

public class ApiManager extends ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    public ApiManager(Context context) {
        mContext = context;
    }

    /**
     * Users API
     */

    public Observable<List<BidPayload>> listUsers() {
        return getUsersApi().listUsers();
    }

    public Observable<BidPayload> getUserById(int id) {
        return getUsersApi().getUserWithId(id);
    }

    /**
     * Auth API
     */
    public Observable<String> login(String username, String password) {
        return getAuthApi().login(username, password);
    }

    public Observable<BidPayload> register(BidPayload payload) {
        return getAuthApi().register(payload);
    }
}
