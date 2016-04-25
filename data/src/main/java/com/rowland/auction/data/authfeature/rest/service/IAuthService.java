package com.rowland.auction.data.authfeature.rest.service;


import com.rowland.auction.data.rest.IApiEndPoint;
import com.rowland.auction.data.userfeature.payload.UserPayload;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IAuthService {

    @POST(IApiEndPoint.AUTH)
    Observable<String> login(@Query("username") String username, @Query("password") String password);

    @POST(IApiEndPoint.USERS)
    Observable<UserPayload> register(@Body UserPayload payload);
}
