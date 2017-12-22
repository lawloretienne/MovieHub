package com.etiennelawlor.moviehub.domain;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonDetailsDomainContract {

    interface UseCase extends BaseUseCase {
        void getPersonDetails(int personId, DisposableSingleObserver disposableSingleObserver);
    }
}
