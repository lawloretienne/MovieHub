package com.etiennelawlor.moviehub.utilities;

import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by etiennelawlor on 12/4/16.
 */

public class RequestUtility {

    public static Request addQueryParams(Request originalRequest, Map<String, String> queryParamsMap){
        HttpUrl originalHttpUrl = originalRequest.url();
        HttpUrl.Builder httpUrlBuilder = originalHttpUrl.newBuilder();

        for (Map.Entry<String, String> entry : queryParamsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            httpUrlBuilder.addQueryParameter(key, value);
        }

        HttpUrl httpUrl = httpUrlBuilder.build();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                .url(httpUrl);

        return requestBuilder.build();
    }

    public static Request updateHeaders(Request originalRequest, Map<String, String> headersMap){
        Request.Builder requestBuilder = originalRequest.newBuilder();

        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            requestBuilder.header(key, value);
        }

        return requestBuilder.build();
    }
}
