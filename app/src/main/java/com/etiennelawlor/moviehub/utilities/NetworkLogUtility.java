package com.etiennelawlor.moviehub.utilities;

import retrofit2.Call;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 6/1/16.
 */

public class NetworkLogUtility {

    public static void logFailure(Call call, Throwable throwable){
        if(call != null){
            if (call.isCanceled())
                Timber.e("Request was cancelled");

//            Request request = call.request();
//            if(request != null){
//                HttpUrl httpUrl = request.url();
//                if(httpUrl != null){
//                    Timber.e(String.format("logFailure() : %s : failed", httpUrl));
//                }
//            }
        }

        if(throwable != null){
            Throwable cause = throwable.getCause();
//            String message = throwable.getMessage();

            if (cause != null) {
                Timber.e(String.format("logFailure() : cause.toString() : %s", cause.toString()));
            }

//            if (!TextUtils.isEmpty(message)) {
//                Timber.e(String.format("logFailure() : message : %s", message));
//            }

//            throwable.printStackTrace();
        }
    }
}
