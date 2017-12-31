package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsDataModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowsDomainContract {

    interface UseCase {
        Single<TelevisionShowsDataModel> getPopularTelevisionShows(int currentPage);
    }
}
