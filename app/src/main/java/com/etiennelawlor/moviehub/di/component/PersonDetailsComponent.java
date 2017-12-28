package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.PersonDetailsModule;
import com.etiennelawlor.moviehub.di.scope.PersonsScope;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@PersonsScope
@Subcomponent(modules = {PersonDetailsModule.class})
public interface PersonDetailsComponent {
    // Setup injection targets
    void inject(PersonDetailsFragment target);
}
