package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonDetailsDomainContract {

    interface UseCase {
        Single<PersonDetailsDomainModel> getPersonDetails(int personId);
    }
}
