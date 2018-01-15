package com.etiennelawlor.moviehub;

import android.app.Application;
import android.content.Context;

import com.etiennelawlor.moviehub.data.network.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.di.module.NetworkModule;

import org.mockito.Mockito;

import dagger.Module;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;

/**
 * Created by etiennelawlor on 1/15/18.
 */

@Module
public class TestNetworkModule extends NetworkModule {

    @Override
    public Cache provideCache(Application application) {
        return mock(Cache.class);
    }

    @Override
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return mock(HttpLoggingInterceptor.class);
    }

    @Override
    public OkHttpClient provideOkHttpClient(Cache cache, AuthorizedNetworkInterceptor authorizedNetworkInterceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        return mock(OkHttpClient.class);
    }


    @Override
    public AuthorizedNetworkInterceptor provideAuthorizedNetworkInterceptor(Context context) {
        return mock(AuthorizedNetworkInterceptor.class);
    }

    @Override
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return mock(Retrofit.class);
    }

    @Override
    public MovieHubService provideMovieHubService(Retrofit retrofit) {
        return mock(MovieHubService.class);
    }
}
