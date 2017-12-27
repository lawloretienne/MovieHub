package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.SearchModule;
import com.etiennelawlor.moviehub.di.scope.PersonsScope;
import com.etiennelawlor.moviehub.presentation.search.SearchFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@PersonsScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    void inject(SearchFragment target);
}
