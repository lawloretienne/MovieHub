package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

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
    public Maybe<MoviesDataModel> getMovieSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveMovieSearchResults(MoviesDataModel moviesDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<TelevisionShowsDataModel> getTelevisionShowSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShowSearchResults(TelevisionShowsDataModel televisionShowsDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<PersonsDataModel> getPersonSearchResults(String query, int page) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void savePersonSearchResults(PersonsDataModel personsDataModel) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
