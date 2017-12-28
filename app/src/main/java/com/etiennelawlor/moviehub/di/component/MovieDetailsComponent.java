package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.MovieDetailsModule;
import com.etiennelawlor.moviehub.di.scope.MoviesScope;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@MoviesScope
@Subcomponent(modules = {MovieDetailsModule.class})
public interface MovieDetailsComponent {
    // Setup injection targets
    void inject(MovieDetailsFragment target);
}
