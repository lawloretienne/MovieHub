package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.FragmentModule;
import com.etiennelawlor.moviehub.di.module.MoviesModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;
import com.etiennelawlor.moviehub.presentation.movies.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        MoviesModule.class})
public interface ApplicationComponent {
    void inject(MovieHubApplication target);
}
