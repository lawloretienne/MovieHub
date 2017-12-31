package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface SearchDataSourceContract {

    interface Repository {
        Single<MoviesEnvelope> getMovieSearchResults(String query, int page);
        Single<TelevisionShowsEnvelope> getTelevisionShowSearchResults(String query, int page);
        Single<PersonsEnvelope> getPersonSearchResults(String query, int page);
    }

    interface LocalDateSource {
        Maybe<MoviesEnvelope> getMovieSearchResults(String query, int page);

        void saveMovieSearchResults(MoviesEnvelope moviesEnvelope);

        Maybe<TelevisionShowsEnvelope> getTelevisionShowSearchResults(String query, int page);

        void saveTelevisionShowSearchResults(TelevisionShowsEnvelope televisionShowsEnvelope);

        Maybe<PersonsEnvelope> getPersonSearchResults(String query, int page);

        void savePersonSearchResults(PersonsEnvelope personsEnvelope);
    }

    interface RemoteDateSource {
        Single<MoviesEnvelope> getMovieSearchResults(String query, int page);
        Single<TelevisionShowsEnvelope> getTelevisionShowSearchResults(String query, int page);
        Single<PersonsEnvelope> getPersonSearchResults(String query, int page);
    }
}
