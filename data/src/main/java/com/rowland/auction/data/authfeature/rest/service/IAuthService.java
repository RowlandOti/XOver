package com.rowland.auction.data.authfeature.rest.service;


import com.rowland.auction.data.rest.IApiEndPoint;
import com.rowland.auction.data.userfeature.payload.UserPayload;

import retrofit2.Callback;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAuthService {

    @POST(IApiEndPoint.AUTH)
    void authenticate(@Query("username") String username, @Query("password") String password, Callback<UserPayload> userCallback);

}
