package com.etiennelawlor.moviehub.data.source.search;

import com.etiennelawlor.moviehub.data.model.SearchWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface SearchDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<SearchWrapper> getSearch(String query);
    }

    interface LocalDateSource {
        Observable<SearchWrapper> getSearch(String query);
        void saveSearch(SearchWrapper searchWrapper);
    }

    interface RemoteDateSource {
         Observable<SearchWrapper> getSearch(String query);
    }
}
