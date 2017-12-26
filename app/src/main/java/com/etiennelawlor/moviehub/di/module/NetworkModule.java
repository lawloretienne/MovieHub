package com.etiennelawlor.moviehub.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.etiennelawlor.moviehub.BuildConfig;
import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.data.network.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.network.MovieHubService;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 12/25/17.
 */

@Module
public class NetworkModule {

//    private String baseUrl;

    private static String BASE_URL = "https://api.themoviedb.org/3/";


    // Constructor needs one parameter to instantiate.
//    public NetworkModule(String baseUrl) {
//        this.baseUrl = baseUrl;
//    }

    public NetworkModule() {
    }

    @Provides
    @Singleton
    public Cache provideCache(MovieHubApplication application) {
        int cacheSize = 10 * 1024 * 1024; // 10MB
        Cache cache = null;
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(application.getCacheDir(), "http");
            cache = new Cache(cacheDir, cacheSize);
        } catch (Exception e) {
            Timber.e(e, "Unable to install disk cache.");
        }
        return cache;
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

//    @Provides
//    @Singleton
//    Gson provideGson() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//        return gsonBuilder.create();
//    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache, AuthorizedNetworkInterceptor authorizedNetworkInterceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.cache(cache);

        if(authorizedNetworkInterceptor != null){
            okHttpClientBuilder.addNetworkInterceptor(authorizedNetworkInterceptor);
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    public AuthorizedNetworkInterceptor provideAuthorizedNetworkInterceptor(Context context) {
        return new AuthorizedNetworkInterceptor(context);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public MovieHubService provideMovieHubService(Retrofit retrofit) {
        return retrofit.create(MovieHubService.class);
    }
}
