package com.etiennelawlor.moviehub.data.network;

import android.content.Context;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.util.RequestUtility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by etiennelawlor on 12/5/16.
 */

public class AuthorizedNetworkInterceptor implements Interceptor {

    // region Constants
    private static final String KEY_API_KEY = "api_key";
    private static final String KEY_LANGUAGE = "language";
    // endregion

    // region Member Variables
    private Context context;
    // endregion

    // region Constructors
    public AuthorizedNetworkInterceptor(Context context) {
        this.context = context;
    }
    // endregion

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain != null) {
            Request originalRequest = chain.request();

            Map<String, String> queryParamsMap = new HashMap<>();
            queryParamsMap.put(KEY_API_KEY, context.getString(R.string.api_key));
            queryParamsMap.put(KEY_LANGUAGE, "en-US");
            Request modifiedRequest = RequestUtility.addQueryParams(originalRequest, queryParamsMap);

            return chain.proceed(modifiedRequest);
        }

        return null;
    }
}
