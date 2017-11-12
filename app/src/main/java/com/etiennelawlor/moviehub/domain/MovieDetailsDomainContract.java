package com.etiennelawlor.moviehub.domain;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MovieDetailsDomainContract {

    interface UseCase extends BaseUseCase {
        void getMovieDetails(int movieId, Subscriber subscriber);
    }
}
