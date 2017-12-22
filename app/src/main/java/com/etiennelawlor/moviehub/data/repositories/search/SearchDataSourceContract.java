package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface SearchDataSourceContract {

    interface Repository {
        //        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Single<SearchWrapper> getSearch(String query);
    }

    interface LocalDateSource {
        Maybe<SearchWrapper> getSearch(String query);

        void saveSearch(SearchWrapper searchWrapper);
    }

    interface RemoteDateSource {
        Single<SearchWrapper> getSearch(String query);
    }
}
