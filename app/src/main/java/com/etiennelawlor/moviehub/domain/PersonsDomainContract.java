package com.etiennelawlor.moviehub.domain;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonsDomainContract {

    interface UseCase extends BaseUseCase {
        void getPopularPersons(int currentPage, DisposableSingleObserver disposableSingleObserver);
    }
}
