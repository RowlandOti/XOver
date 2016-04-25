package com.rowland.auction.data.userfeature.rest.service;

import com.rowland.auction.data.rest.IApiEndPoint;
import com.rowland.auction.data.userfeature.payload.UserPayload;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface IUserService {

    @GET("users.json")
    Observable<List<UserPayload>> listUsers();

    @GET("user_{userId}.json")
    Observable<UserPayload> getUserWithId(@Path("userId") int userId);
}
