package com.rowland.auction.data.userfeature.rest.service;

import com.rowland.auction.data.userfeature.payload.BidPayload;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface IUserService {

    @GET("users.json")
    Observable<List<BidPayload>> listUsers();

    @GET("user_{userId}.json")
    Observable<BidPayload> getUserWithId(@Path("userId") int userId);
}
