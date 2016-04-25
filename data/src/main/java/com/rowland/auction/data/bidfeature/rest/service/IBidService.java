package com.rowland.auction.data.bidfeature.rest.service;


import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.data.rest.IApiEndPoint;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface IBidService {

    @GET(IApiEndPoint.BIDS)
    Observable<List<BidPayload>> listBids();

    @GET(IApiEndPoint.BIDS)
    Observable<BidPayload> getBidWithId(@Query("id") int bidId);

    @POST(IApiEndPoint.USERS)
    Observable<BidPayload> create(@Body BidPayload payload);
}
