package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.person.PersonRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRemoteDataSource;
import com.etiennelawlor.moviehub.di.module.NetworkModule;
import com.etiennelawlor.moviehub.di.scope.DataScope;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@DataScope
@Subcomponent(modules = {NetworkModule.class})
public interface NetworkComponent {
    // Setup injection targets
    void inject(MovieRemoteDataSource target);
    void inject(TelevisionShowRemoteDataSource target);
    void inject(PersonRemoteDataSource target);
    void inject(SearchRemoteDataSource target);
}
