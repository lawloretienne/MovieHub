package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchRemoteDataSource implements SearchDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    @Inject
    public SearchRemoteDataSource(MovieHubService movieHubService) {
        this.movieHubService = movieHubService;
    }
    // endregion

    // region SearchDataSourceContract.RemoteDateSource Methods
    @Override
    public Single<MoviesEnvelope> getMovieSearchResults(String query, int page) {
        return movieHubService.getMovieSearchResults(query, page);
    }

    @Override
    public Single<TelevisionShowsEnvelope> getTelevisionShowSearchResults(String query, int page) {
        return movieHubService.getTelevisionShowSearchResults(query, page);
    }

    @Override
    public Single<PersonsEnvelope> getPersonSearchResults(String query, int page) {
        return movieHubService.getPersonSearchResults(query, page);
    }

    // endregion
}
