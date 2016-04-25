package com.rowland.auction.data.dashboardfeature.rest.service;


import com.rowland.auction.data.dashboardfeature.payload.BidPayload;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface IBidService {

    @GET("users.json")
    Observable<List<BidPayload>> listUsers();

    @GET("user_{userId}.json")
    Observable<BidPayload> getUserWithId(@Path("userId") int userId);
}
