package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;

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
    public Single<MoviesResponse> getMovieSearchResults(String query, int page) {
        return movieHubService.getMovieSearchResults(query, page);
    }

    @Override
    public Single<TelevisionShowsResponse> getTelevisionShowSearchResults(String query, int page) {
        return movieHubService.getTelevisionShowSearchResults(query, page);
    }

    @Override
    public Single<PersonsResponse> getPersonSearchResults(String query, int page) {
        return movieHubService.getPersonSearchResults(query, page);
    }

    // endregion
}
