package com.etiennelawlor.moviehub.domain;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowsDomainContract {

    interface UseCase extends BaseUseCase {
        void getPopularTelevisionShows(int currentPage, Subscriber subscriber);
    }
}
