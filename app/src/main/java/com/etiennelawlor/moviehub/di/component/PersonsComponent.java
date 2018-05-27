package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.PersonsModule;
import com.etiennelawlor.moviehub.di.scope.PresentationScope;
import com.etiennelawlor.moviehub.presentation.persons.PersonsFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@PresentationScope
@Subcomponent(modules = {PersonsModule.class})
public interface PersonsComponent {
    // Setup injection targets
    void inject(PersonsFragment target);
}
