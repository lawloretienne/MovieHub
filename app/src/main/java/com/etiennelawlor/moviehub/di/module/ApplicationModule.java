package com.etiennelawlor.moviehub.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.etiennelawlor.moviehub.data.network.MovieHubService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public MovieHubService provideMovieHubService(Retrofit retrofit) {
        return retrofit.create(MovieHubService.class);
    }
}
