package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface SearchDataSourceContract {

    interface Repository {
        Single<MoviesResponse> getMovieSearchResults(String query, int page);
        Single<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page);
        Single<PersonsResponse> getPersonSearchResults(String query, int page);
    }

    interface LocalDateSource {
        Maybe<MoviesResponse> getMovieSearchResults(String query, int page);

        void saveMovieSearchResults(MoviesResponse moviesResponse);

        Maybe<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page);

        void saveTelevisionShowSearchResults(TelevisionShowsResponse televisionShowsResponse);

        Maybe<PersonsResponse> getPersonSearchResults(String query, int page);

        void savePersonSearchResults(PersonsResponse personsResponse);
    }

    interface RemoteDateSource {
        Single<MoviesResponse> getMovieSearchResults(String query, int page);
        Single<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page);
        Single<PersonsResponse> getPersonSearchResults(String query, int page);
    }
}
