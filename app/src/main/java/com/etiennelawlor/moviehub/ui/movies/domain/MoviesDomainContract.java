package com.etiennelawlor.moviehub.ui.movies.domain;

import com.etiennelawlor.moviehub.ui.base.BaseUseCase;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MoviesDomainContract {

    interface UseCase extends BaseUseCase {
        void getPopularMovies(int currentPage, Subscriber subscriber);
    }
}
