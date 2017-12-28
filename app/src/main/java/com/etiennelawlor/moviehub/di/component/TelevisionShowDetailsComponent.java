package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.TelevisionShowDetailsModule;
import com.etiennelawlor.moviehub.di.scope.TelevisionShowsScope;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@TelevisionShowsScope
@Subcomponent(modules = {TelevisionShowDetailsModule.class})
public interface TelevisionShowDetailsComponent {
    // Setup injection targets
    void inject(TelevisionShowDetailsFragment target);
}
