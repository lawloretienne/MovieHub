package com.etiennelawlor.moviehub.data.source.movies;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataSource {

    Observable getMovies(int currentPage);
}
