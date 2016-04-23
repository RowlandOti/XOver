package com.rowland.auction.presentation.api;

import com.rowland.auction.data.rest.IApiEndPoint;

public class ApiApiEndPoint implements IApiEndPoint {

    private String url;

    @Override
    public void updateInstanceUrl(String instanceUrl) {
        this.url = instanceUrl;
    }

    @Override
    public String getUrl() {
        if (url == null)
            return PROTOCOL_HTTPS + API_BASE_URL_ENDPOINT + API_PATH;
        return url;
    }

    @Override
    public String getName() {
        return "doea";
    }
}
