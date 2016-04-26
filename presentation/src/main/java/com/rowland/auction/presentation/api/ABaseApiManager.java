package com.rowland.auction.presentation.api;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.rowland.auction.data.authfeature.rest.service.IAuthService;
import com.rowland.auction.data.bidfeature.rest.service.IBidService;
import com.rowland.auction.data.rest.interceptor.ApiSessionRequestInterceptor;
import com.rowland.auction.data.userfeature.rest.service.IUserService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    private ApiApiEndPoint API_ENDPOINT = new ApiApiEndPoint();

    private IUserService mUserApi;
    private IAuthService mAuthApi;
    private IBidService mBidApi;
    protected Context mContext;

    public ABaseApiManager() {
        createAuthApi();
        mUserApi = createApi(IUserService.class, API_ENDPOINT);
        mBidApi = createApi(IBidService.class, API_ENDPOINT);
    }

    public void setupEndpoint(String instanceUrl) {
        API_ENDPOINT.updateInstanceUrl(instanceUrl);
    }

    private OkHttpClient generateClientInterceptors() {
        ApiSessionRequestInterceptor apiSessionRequestInterceptor = new ApiSessionRequestInterceptor();
        // Our client
        OkHttpClient client = new OkHttpClient();
        // Set ApiSessionRequestInterceptor instance as interceptor
        client.interceptors().add(apiSessionRequestInterceptor);
        return client;
    }

    private <T> T createApi(Class<T> clazz, ApiApiEndPoint endpoint) {
        return new Retrofit.Builder()
                .baseUrl(endpoint.getUrl())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //.client(generateClientInterceptors())
                .build()
                .create(clazz);
    }

    private void createAuthApi() {
        mAuthApi = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT.getUrl())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //.client(generateClientInterceptors())
                .build()
                .create(IAuthService.class);
    }

    protected IAuthService getAuthApi() {
        return mAuthApi;
    }

    protected IUserService getUsersApi() {
        return mUserApi;
    }

    protected IBidService getBidsApi() {
        return mBidApi;
    }

    public Context getContext() {
        return mContext;
    }
}
