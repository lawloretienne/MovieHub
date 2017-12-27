package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.MoviesModule;
import com.etiennelawlor.moviehub.di.scope.MovieDetailsScope;
import com.etiennelawlor.moviehub.presentation.movies.MoviesFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@MovieDetailsScope
@Subcomponent(modules = {MoviesModule.class})
public interface MoviesComponent {
    void inject(MoviesFragment target);
}
