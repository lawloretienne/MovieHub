package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonsDomainContract {

    interface UseCase {
        Single<PersonsDomainModel> getPopularPersons(int currentPage);
    }
}
