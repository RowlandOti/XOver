package com.doea.app.presentation.api;

import android.content.Context;
import android.util.Log;

import com.doea.app.data.BuildConfig;
import com.doea.app.data.userfeature.payload.UserPayload;

import java.util.List;

import retrofit2.Callback;
import rx.Observable;

public class ApiManager extends ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    public ApiManager(Context context) {
        mContext = context;
    }

    public void login(String username, String password, Callback<UserPayload> callback) {
        if(BuildConfig.DEBUG) {
            Log.i(LOG_TAG, "Login - " + username + " Password - " + password);
        }
        getAuthApi().authenticate(username, password, callback);
    }

    /**
     * Users API
     */

    public Observable<List<UserPayload>> listUsers() {
       return getUsersApi().listUsers();
    }

    public Observable<UserPayload> getUserById(int id) {
        return getUsersApi().getUserWithId(id);
    }

    public void createUser(UserPayload payload) {
        getUsersApi().createUser(payload);
    }
}
