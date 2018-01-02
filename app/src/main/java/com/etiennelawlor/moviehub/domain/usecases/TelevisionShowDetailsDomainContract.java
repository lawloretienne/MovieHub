package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowDetailsDomainContract {

    interface UseCase {
        Single<TelevisionShowDetailsDomainModel> getTelevisionShowDetails(int televisionShowId);
    }
}
