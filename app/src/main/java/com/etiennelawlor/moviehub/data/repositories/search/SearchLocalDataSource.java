package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;

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
    public Maybe<MoviesEnvelope> getMovieSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieSearchResults(MoviesEnvelope moviesEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<TelevisionShowsEnvelope> getTelevisionShowSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShowSearchResults(TelevisionShowsEnvelope televisionShowsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<PersonsEnvelope> getPersonSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePersonSearchResults(PersonsEnvelope personsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
