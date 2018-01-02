package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MovieDetailsDomainContract {

    interface UseCase {
        Single<MovieDetailsDomainModel> getMovieDetails(int movieId);
    }
}
