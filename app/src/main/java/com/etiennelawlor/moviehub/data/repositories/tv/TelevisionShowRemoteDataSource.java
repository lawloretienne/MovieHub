package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowRemoteDataSource implements TelevisionShowDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    @Inject
    public TelevisionShowRemoteDataSource(MovieHubService movieHubService) {
        this.movieHubService = movieHubService;
    }
    // endregion

    // region TelevisionShowDataSourceContract.RemoteDateSource Methods
    @Override
    public Single<TelevisionShowsResponse> getPopularTelevisionShows(int currentPage) {
        return movieHubService.getPopularTelevisionShows(currentPage);
    }

    @Override
    public Single<TelevisionShow> getTelevisionShow(int tvId) {
        return movieHubService.getTelevisionShow(tvId);
    }

    @Override
    public Single<TelevisionShowCreditsResponse> getTelevisionShowCredits(int tvId) {
        return movieHubService.getTelevisionShowCredits(tvId);
    }

    @Override
    public Single<TelevisionShowsResponse> getSimilarTelevisionShows(int tvId) {
        return movieHubService.getSimilarTelevisionShows(tvId);
    }

    @Override
    public Single<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(int tvId) {
        return movieHubService.getTelevisionShowContentRatings(tvId);
    }

    // endregion
}
