package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.di.module.SearchModule;
import com.etiennelawlor.moviehub.di.scope.PresentationScope;
import com.etiennelawlor.moviehub.presentation.search.SearchFragment;

import dagger.Subcomponent;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@PresentationScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchComponent {
    // Setup injection targets
    void inject(SearchFragment target);
}
