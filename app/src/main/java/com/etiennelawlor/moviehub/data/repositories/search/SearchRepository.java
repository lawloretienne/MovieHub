package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.repositories.mappers.MoviesDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.PersonsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchRepository implements SearchDataSourceContract.Repository {

    // region Member Variables
    private SearchDataSourceContract.LocalDateSource searchLocalDataSource;
    private SearchDataSourceContract.RemoteDateSource searchRemoteDataSource;
    private MoviesDataModelMapper moviesDataModelMapper = new MoviesDataModelMapper();
    private PersonsDataModelMapper personsDataModelMapper = new PersonsDataModelMapper();
    private TelevisionShowsDataModelMapper televisionShowsDataModelMapper = new TelevisionShowsDataModelMapper();
    // endregion

    // region Constructors
    public SearchRepository(SearchDataSourceContract.LocalDateSource searchLocalDataSource, SearchDataSourceContract.RemoteDateSource searchRemoteDataSource) {
        this.searchLocalDataSource = searchLocalDataSource;
        this.searchRemoteDataSource = searchRemoteDataSource;
    }
    // endregion

    // region SearchDataSourceContract.Repository Methods
    @Override
    public Single<MoviesDataModel> getMovieSearchResults(String query, int page) {
        Maybe<MoviesDataModel> local = searchLocalDataSource.getMovieSearchResults(query, page);
        Single<MoviesDataModel> remote =
                searchRemoteDataSource.getMovieSearchResults(query, page)
                        .map(moviesResponse -> moviesDataModelMapper.mapToDataModel(moviesResponse))
                        .doOnSuccess(moviesDataModel -> searchLocalDataSource.saveMovieSearchResults(moviesDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowsDataModel> getTelevisionShowSearchResults(String query, int page) {
        Maybe<TelevisionShowsDataModel> local = searchLocalDataSource.getTelevisionShowSearchResults(query, page);
        Single<TelevisionShowsDataModel> remote =
                searchRemoteDataSource.getTelevisionShowSearchResults(query, page)
                        .map(televisionShowsResponse -> televisionShowsDataModelMapper.mapToDataModel(televisionShowsResponse))
                        .doOnSuccess(televisionShowsDataModel -> searchLocalDataSource.saveTelevisionShowSearchResults(televisionShowsDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<PersonsDataModel> getPersonSearchResults(String query, int page) {
        Maybe<PersonsDataModel> local = searchLocalDataSource.getPersonSearchResults(query, page);
        Single<PersonsDataModel> remote =
                searchRemoteDataSource.getPersonSearchResults(query, page)
                        .map(personsResponse -> personsDataModelMapper.mapToDataModel(personsResponse))
                        .doOnSuccess(personsResponse -> searchLocalDataSource.savePersonSearchResults(personsResponse));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
