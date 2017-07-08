package com.etiennelawlor.moviehub.data.source.search;

import android.content.Context;

import com.etiennelawlor.moviehub.data.source.search.models.SearchWrapper;

import rx.Observable;

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
    public Observable<SearchWrapper> getSearch(String query) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void saveSearch(SearchWrapper searchWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
