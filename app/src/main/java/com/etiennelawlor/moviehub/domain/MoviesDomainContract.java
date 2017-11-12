package com.etiennelawlor.moviehub.domain;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MoviesDomainContract {

    interface UseCase extends BaseUseCase {
        void getPopularMovies(int currentPage, Subscriber subscriber);
    }
}
