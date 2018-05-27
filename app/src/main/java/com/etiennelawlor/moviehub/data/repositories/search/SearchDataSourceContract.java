package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface SearchDataSourceContract {

    interface Repository {
        Single<MoviesDataModel> getMovieSearchResults(String query, int page);
        Single<TelevisionShowsDataModel> getTelevisionShowSearchResults(String query, int page);
        Single<PersonsDataModel> getPersonSearchResults(String query, int page);
    }

    interface LocalDateSource {
        Maybe<MoviesDataModel> getMovieSearchResults(String query, int page);
        void saveMovieSearchResults(MoviesDataModel moviesDataModel);
        Maybe<TelevisionShowsDataModel> getTelevisionShowSearchResults(String query, int page);
        void saveTelevisionShowSearchResults(TelevisionShowsDataModel televisionShowsDataModel);
        Maybe<PersonsDataModel> getPersonSearchResults(String query, int page);
        void savePersonSearchResults(PersonsDataModel personsDataModel);
    }

    interface RemoteDateSource {
        Single<MoviesResponse> getMovieSearchResults(String query, int page);
        Single<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page);
        Single<PersonsResponse> getPersonSearchResults(String query, int page);
    }
}
