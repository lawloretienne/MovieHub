package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowsDomainContract {

    interface UseCase {
        Single<TelevisionShowsDomainModel> getPopularTelevisionShows(int currentPage);
    }
}
