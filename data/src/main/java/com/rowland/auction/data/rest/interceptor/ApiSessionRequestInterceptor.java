/*
 * Copyright 2015 Oti Rowland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rowland.auction.data.rest.interceptor;

import com.rowland.auction.data.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Oti Rowland on 12/23/2015.
 */
public class ApiSessionRequestInterceptor implements Interceptor {

    // Headers
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_API_KEY = "Authorization";
    public static final String HEADER_FLAVOUR = "Flavour";
    public static final String HEADER_VERSION = "Version";

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Get a Request object
        Request request = chain.request();
        // Check authentication
        //if (PrefManager.isAuthenticated()) {
        // Add headers to the Request object
        request = request.newBuilder()
                .addHeader(HEADER_USER_AGENT, BuildConfig.APPLICATION_ID)
                .addHeader(HEADER_VERSION, BuildConfig.VERSION_NAME)
                .addHeader(HEADER_FLAVOUR, BuildConfig.FLAVOR)
                //.addHeader(HEADER_API_KEY, PrefManager.getToken())
                .build();
        //}
        // Chain request
        Response response = chain.proceed(request);
        // Return response
        return response;
    }
}