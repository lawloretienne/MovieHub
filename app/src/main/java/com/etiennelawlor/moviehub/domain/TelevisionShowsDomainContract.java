package com.etiennelawlor.moviehub.domain;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowsDomainContract {

    interface UseCase extends BaseUseCase {
        void getPopularTelevisionShows(int currentPage, DisposableSingleObserver disposableSingleObserver);
    }
}
