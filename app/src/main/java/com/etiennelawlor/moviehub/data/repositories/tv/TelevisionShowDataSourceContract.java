package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowContentRatingsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface TelevisionShowDataSourceContract {

    interface Repository {
        Single<TelevisionShowsDataModel> getPopularTelevisionShows(int currentPage);
        Single<TelevisionShowDataModel> getTelevisionShow(long tvId);
        Single<TelevisionShowCreditsDataModel> getTelevisionShowCredits(long tvId);
        Single<TelevisionShowsDataModel> getSimilarTelevisionShows(long tvId);
        Single<TelevisionShowContentRatingsDataModel> getTelevisionShowContentRatings(long tvId);
    }

    interface LocalDateSource {
        Maybe<TelevisionShowsDataModel> getPopularTelevisionShows(int currentPage);
        void savePopularTelevisionShows(TelevisionShowsDataModel televisionShowsDataModel);
        Maybe<TelevisionShowDataModel> getTelevisionShow(long tvId);
        void saveTelevisionShow(TelevisionShowDataModel televisionShowDataModel);
        Maybe<TelevisionShowCreditsDataModel> getTelevisionShowCredits(long tvId);
        void saveTelevisionShowCredits(TelevisionShowCreditsDataModel televisionShowCreditsDataModel);
        Maybe<TelevisionShowsDataModel> getSimilarTelevisionShows(long tvId);
        void saveSimilarTelevisionShows(TelevisionShowsDataModel televisionShowsDataModel);
        Maybe<TelevisionShowContentRatingsDataModel> getTelevisionShowContentRatings(long tvId);
        void saveTelevisionShowContentRatings(TelevisionShowContentRatingsDataModel televisionShowContentRatingsResponse);
    }

    interface RemoteDateSource {
        Single<TelevisionShowsResponse> getPopularTelevisionShows(int currentPage);
        Single<TelevisionShowResponse> getTelevisionShow(long tvId);
        Single<TelevisionShowCreditsResponse> getTelevisionShowCredits(long tvId);
        Single<TelevisionShowsResponse> getSimilarTelevisionShows(long tvId);
        Single<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(long tvId);
    }
}
