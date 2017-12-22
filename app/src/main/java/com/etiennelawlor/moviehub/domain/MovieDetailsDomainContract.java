package com.etiennelawlor.moviehub.domain;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MovieDetailsDomainContract {

    interface UseCase extends BaseUseCase {
        void getMovieDetails(int movieId, DisposableSingleObserver disposableSingleObserver);
    }
}
