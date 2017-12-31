package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;

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
    public Single<MoviesEnvelope> getMovieSearchResults(String query, int page) {
        Maybe<MoviesEnvelope> local = searchLocalDataSource.getMovieSearchResults(query, page);
        Single<MoviesEnvelope> remote =
                searchRemoteDataSource.getMovieSearchResults(query, page)
                        .doOnSuccess(moviesEnvelope -> searchLocalDataSource.saveMovieSearchResults(moviesEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowsEnvelope> getTelevisionShowSearchResults(String query, int page) {
        Maybe<TelevisionShowsEnvelope> local = searchLocalDataSource.getTelevisionShowSearchResults(query, page);
        Single<TelevisionShowsEnvelope> remote =
                searchRemoteDataSource.getTelevisionShowSearchResults(query, page)
                        .doOnSuccess(televisionShowsEnvelope -> searchLocalDataSource.saveTelevisionShowSearchResults(televisionShowsEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonsEnvelope> getPersonSearchResults(String query, int page) {
        Maybe<PersonsEnvelope> local = searchLocalDataSource.getPersonSearchResults(query, page);
        Single<PersonsEnvelope> remote =
                searchRemoteDataSource.getPersonSearchResults(query, page)
                        .doOnSuccess(personsEnvelope -> searchLocalDataSource.savePersonSearchResults(personsEnvelope));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
