package com.doea.app.data.rest;

public interface IApiEndPoint {

    // The Base url EndPoints
    //String API_BASE_URL_ENDPOINT = "http://api.doea.com/vbeta/";
    String API_BASE_URL_ENDPOINT = "www.android10.org";
    String API_PATH = "/myapi/";
    // What protocol to use
    String PROTOCOL_HTTPS = "http://";
    // This class contains all the Constants for API End Points
    String AUTH = "/auth";
    String USERS = "/users";

    void updateInstanceUrl(String instanceUrl);

    String getUrl();

    String getName();
}
