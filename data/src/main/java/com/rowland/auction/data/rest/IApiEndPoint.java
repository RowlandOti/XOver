package com.rowland.auction.data.rest;

public interface IApiEndPoint {

    // The Base url EndPoints
    //String API_BASE_URL_ENDPOINT = "http://api.doea.com/vbeta/";
    String API_BASE_URL_ENDPOINT = "api.skyllabler.com";
    String API_PATH = "/v1/";
    // What protocol to use
    String PROTOCOL_HTTPS = "http://";
    // This class contains all the Constants for API End Points
    String AUTH = "/auth";
    String USERS = "/users";
    String BIDS = "/bids";

    void updateInstanceUrl(String instanceUrl);

    String getUrl();

    String getName();
}
