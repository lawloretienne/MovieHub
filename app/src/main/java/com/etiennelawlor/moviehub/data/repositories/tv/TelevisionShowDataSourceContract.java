package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;
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
        Single<TelevisionShowDataModel> getTelevisionShow(int tvId);
        Single<TelevisionShowCreditsDataModel> getTelevisionShowCredits(int tvId);
        Single<TelevisionShowsDataModel> getSimilarTelevisionShows(int tvId);
        Single<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(int tvId);
    }

    interface LocalDateSource {
        Maybe<TelevisionShowsDataModel> getPopularTelevisionShows(int currentPage);
        void savePopularTelevisionShows(TelevisionShowsDataModel televisionShowsDataModel);

        Maybe<TelevisionShowDataModel> getTelevisionShow(int tvId);
        void saveTelevisionShow(TelevisionShowDataModel televisionShowDataModel);

        Maybe<TelevisionShowCreditsDataModel> getTelevisionShowCredits(int tvId);
        void saveTelevisionShowCredits(TelevisionShowCreditsDataModel televisionShowCreditsDataModel);

        Maybe<TelevisionShowsDataModel> getSimilarTelevisionShows(int tvId);
        void saveSimilarTelevisionShows(TelevisionShowsDataModel televisionShowsDataModel);

        Maybe<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(int tvId);
        void saveTelevisionShowContentRatings(TelevisionShowContentRatingsResponse televisionShowContentRatingsResponse);
    }

    interface RemoteDateSource {
        Single<TelevisionShowsResponse> getPopularTelevisionShows(int currentPage);
        Single<TelevisionShowResponse> getTelevisionShow(int tvId);
        Single<TelevisionShowCreditsResponse> getTelevisionShowCredits(int tvId);
        Single<TelevisionShowsResponse> getSimilarTelevisionShows(int tvId);
        Single<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(int tvId);
    }
}
