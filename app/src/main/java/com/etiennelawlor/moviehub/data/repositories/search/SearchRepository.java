package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;

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
    public Single<MoviesResponse> getMovieSearchResults(String query, int page) {
        Maybe<MoviesResponse> local = searchLocalDataSource.getMovieSearchResults(query, page);
        Single<MoviesResponse> remote =
                searchRemoteDataSource.getMovieSearchResults(query, page)
                        .doOnSuccess(moviesResponse -> searchLocalDataSource.saveMovieSearchResults(moviesResponse));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page) {
        Maybe<TelevisionShowsResponse> local = searchLocalDataSource.getTelevisionShowSearchResults(query, page);
        Single<TelevisionShowsResponse> remote =
                searchRemoteDataSource.getTelevisionShowSearchResults(query, page)
                        .doOnSuccess(televisionShowsResponse -> searchLocalDataSource.saveTelevisionShowSearchResults(televisionShowsResponse));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonsResponse> getPersonSearchResults(String query, int page) {
        Maybe<PersonsResponse> local = searchLocalDataSource.getPersonSearchResults(query, page);
        Single<PersonsResponse> remote =
                searchRemoteDataSource.getPersonSearchResults(query, page)
                        .doOnSuccess(personsResponse -> searchLocalDataSource.savePersonSearchResults(personsResponse));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
