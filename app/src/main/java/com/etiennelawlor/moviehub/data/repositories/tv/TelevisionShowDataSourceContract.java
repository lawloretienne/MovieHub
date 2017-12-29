package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface TelevisionShowDataSourceContract {

    interface Repository {
        Single<TelevisionShowsPage> getPopularTelevisionShows(int currentPage);

        Single<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
    }

    interface LocalDateSource {
        Maybe<TelevisionShowsPage> getPopularTelevisionShows(int currentPage);

        void savePopularTelevisionShows(TelevisionShowsPage televisionShowsPage);

        Maybe<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);

        void saveTelevisionShowDetails(TelevisionShowDetailsWrapper televisionShowDetailsWrapper);
    }

    interface RemoteDateSource {
        Single<TelevisionShowsEnvelope> getPopularTelevisionShows(int currentPage);

        Single<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
    }
}
