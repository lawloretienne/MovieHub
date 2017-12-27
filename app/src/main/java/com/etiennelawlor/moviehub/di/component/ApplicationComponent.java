package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.MovieDetailsModule;
import com.etiennelawlor.moviehub.di.module.MoviesModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;
import com.etiennelawlor.moviehub.di.module.PersonsModule;
import com.etiennelawlor.moviehub.di.module.SearchModule;
import com.etiennelawlor.moviehub.di.module.TelevisionShowsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MovieHubApplication target);

    NetworkComponent plus(NetworkModule networkModule);
    MoviesComponent plus(MoviesModule moviesModule);
    MovieDetailsComponent plus(MovieDetailsModule movieDetailsModule);
    TelevisionShowsComponent plus(TelevisionShowsModule televisionShowsModule);
    PersonsComponent plus(PersonsModule personsModule);
    SearchComponent plus(SearchModule searchModule);

}
