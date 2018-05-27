package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.TelevisionShowDetailsModule;
import com.etiennelawlor.moviehub.di.scope.PresentationScope;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@PresentationScope
@Subcomponent(modules = {TelevisionShowDetailsModule.class})
public interface TelevisionShowDetailsComponent {
    // Setup injection targets
    void inject(TelevisionShowDetailsFragment target);
}
