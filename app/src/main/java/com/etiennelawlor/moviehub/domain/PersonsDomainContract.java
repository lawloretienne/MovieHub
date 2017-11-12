package com.etiennelawlor.moviehub.domain;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonsDomainContract {

    interface UseCase extends BaseUseCase {
        void getPopularPersons(int currentPage, Subscriber subscriber);
    }
}
