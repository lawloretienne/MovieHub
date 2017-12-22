package com.etiennelawlor.moviehub.data.repositories.search;

import android.content.Context;

import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

import io.reactivex.Maybe;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchLocalDataSource implements SearchDataSourceContract.LocalDateSource {

    // region Constructors
    public SearchLocalDataSource(Context context) {
    }
    // endregion

    // region SearchDataSourceContract.LocalDateSource Methods

    @Override
    public Maybe<SearchWrapper> getSearch(String query) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveSearch(SearchWrapper searchWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
