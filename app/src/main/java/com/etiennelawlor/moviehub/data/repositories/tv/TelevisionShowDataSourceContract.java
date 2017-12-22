package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;

import io.reactivex.Maybe;
import io.reactivex.Single;
import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface TelevisionShowDataSourceContract {

    interface Repository {
        Single<TelevisionShowsPage> getPopularTelevisionShows(int currentPage);

        Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
    }

    interface LocalDateSource {
        Maybe<TelevisionShowsPage> getPopularTelevisionShows(int currentPage);

        void savePopularTelevisionShows(TelevisionShowsPage televisionShowsPage);

        Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);

        void saveTelevisionShowDetails(TelevisionShowDetailsWrapper televisionShowDetailsWrapper);
    }

    interface RemoteDateSource {
        Single<TelevisionShowsPage> getPopularTelevisionShows(int currentPage);

        Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
    }
}
