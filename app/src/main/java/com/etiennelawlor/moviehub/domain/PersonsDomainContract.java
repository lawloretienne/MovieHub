package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonsDomainContract {

    interface UseCase {
        Single<PersonsDataModel> getPopularPersons(int currentPage);
    }
}
