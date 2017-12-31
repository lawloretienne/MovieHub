package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;

import io.reactivex.Maybe;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchLocalDataSource implements SearchDataSourceContract.LocalDateSource {

    // region Constructors
    public SearchLocalDataSource() {
    }
    // endregion

    // region SearchDataSourceContract.LocalDateSource Methods

    @Override
    public Maybe<MoviesResponse> getMovieSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieSearchResults(MoviesResponse moviesResponse) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShowSearchResults(TelevisionShowsResponse televisionShowsResponse) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<PersonsResponse> getPersonSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePersonSearchResults(PersonsResponse personsResponse) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
