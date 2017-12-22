package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchRepository implements SearchDataSourceContract.Repository {

    // region Member Variables
    private SearchDataSourceContract.LocalDateSource searchLocalDataSource;
    private SearchDataSourceContract.RemoteDateSource searchRemoteDataSource;
    // endregion

    // region Constructors
    public SearchRepository(SearchDataSourceContract.LocalDateSource searchLocalDataSource, SearchDataSourceContract.RemoteDateSource searchRemoteDataSource) {
        this.searchLocalDataSource = searchLocalDataSource;
        this.searchRemoteDataSource = searchRemoteDataSource;
    }
    // endregion

    // region SearchDataSourceContract.Repository Methods
    @Override
    public Single<SearchWrapper> getSearch(String query) {
        Maybe<SearchWrapper> local = searchLocalDataSource.getSearch(query);
        Single<SearchWrapper> remote =
                searchRemoteDataSource.getSearch(query)
                        .doOnSuccess(searchWrapper -> searchLocalDataSource.saveSearch(searchWrapper));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
