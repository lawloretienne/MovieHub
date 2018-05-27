package com.etiennelawlor.moviehub.di.module;

import android.app.Application;
import android.content.Context;

import com.etiennelawlor.moviehub.BuildConfig;
import com.etiennelawlor.moviehub.data.network.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.di.scope.ApplicationScope;

import java.io.File;

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

    public NetworkModule() {
    }

    @Provides
    public Cache provideCache(Application application) {
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
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    @Provides
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
    public AuthorizedNetworkInterceptor provideAuthorizedNetworkInterceptor(Context context) {
        return new AuthorizedNetworkInterceptor(context);
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MovieHubService.BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @ApplicationScope
    public MovieHubService provideMovieHubService(Retrofit retrofit) {
        return retrofit.create(MovieHubService.class);
    }
}
