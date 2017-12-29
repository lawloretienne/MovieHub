package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowDetailsDomainContract {

    interface UseCase {
        Single<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int televisionShowId);
    }
}
