package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonDetailsDomainContract {

    interface UseCase {
        Single<PersonDetailsWrapper> getPersonDetails(int personId);
    }
}
