package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonsDomainContract {

    interface UseCase {
        Single<PersonsPage> getPopularPersons(int currentPage);
    }
}
