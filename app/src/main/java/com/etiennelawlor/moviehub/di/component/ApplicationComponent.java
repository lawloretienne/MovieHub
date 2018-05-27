package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.di.module.AndroidModule;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.MovieDetailsModule;
import com.etiennelawlor.moviehub.di.module.MoviesModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;
import com.etiennelawlor.moviehub.di.module.PersonDetailsModule;
import com.etiennelawlor.moviehub.di.module.PersonsModule;
import com.etiennelawlor.moviehub.di.module.RxModule;
import com.etiennelawlor.moviehub.di.module.SearchModule;
import com.etiennelawlor.moviehub.di.module.TelevisionShowDetailsModule;
import com.etiennelawlor.moviehub.di.module.TelevisionShowsModule;
import com.etiennelawlor.moviehub.di.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, AndroidModule.class, NetworkModule.class, RxModule.class} )
public interface ApplicationComponent {
    // Setup injection targets
    void inject(MovieHubApplication target);

    MoviesComponent createSubcomponent(MoviesModule moviesModule);
    MovieDetailsComponent createSubcomponent(MovieDetailsModule movieDetailsModule);
    TelevisionShowsComponent createSubcomponent(TelevisionShowsModule televisionShowsModule);
    TelevisionShowDetailsComponent createSubcomponent(TelevisionShowDetailsModule televisionShowDetailsModule);
    PersonsComponent createSubcomponent(PersonsModule personsModule);
    PersonDetailsComponent createSubcomponent(PersonDetailsModule personDetailsModule);
    SearchComponent createSubcomponent(SearchModule searchModule);
}
