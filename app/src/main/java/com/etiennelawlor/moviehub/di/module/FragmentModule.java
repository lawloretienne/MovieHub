package com.etiennelawlor.moviehub.di.module;

import android.support.v4.app.Fragment;

import com.etiennelawlor.moviehub.presentation.movies.MoviesUiContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() { return this.fragment; }

    // here you could also provide your view implementation...
    @Provides MoviesUiContract.View provideMoviesView() {
        return (MoviesUiContract.View) this.fragment;
    }
}
