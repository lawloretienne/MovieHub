package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.PersonsModule;
import com.etiennelawlor.moviehub.di.scope.PersonsScope;
import com.etiennelawlor.moviehub.presentation.persons.PersonsFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@PersonsScope
@Subcomponent(modules = {PersonsModule.class})
public interface PersonsComponent {
    void inject(PersonsFragment target);
}
