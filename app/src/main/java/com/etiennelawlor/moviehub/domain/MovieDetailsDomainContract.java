package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MovieDetailsDomainContract {

    interface UseCase {
        Single<MovieDetailsWrapper> getMovieDetails(int movieId);
    }
}
