package com.doea.app.data.userfeature.rest.service;

import com.doea.app.data.rest.IApiEndPoint;
import com.doea.app.data.userfeature.payload.UserPayload;

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

    @POST(IApiEndPoint.USERS)
    Observable<UserPayload> createUser(UserPayload payload);
}
