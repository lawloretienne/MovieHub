package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MoviesDataModelMapper implements DataModelMapper<MoviesResponse, MoviesDataModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    @Override
    public MoviesDataModel mapToDataModel(MoviesResponse moviesResponse) {
        MoviesDataModel moviesDataModel = new MoviesDataModel();

        List<Movie> movies = moviesResponse.getMovies();
        moviesDataModel.setLastPage(movies.size() < PAGE_SIZE ? true : false);
        moviesDataModel.setPageNumber(moviesResponse.getPage());
        moviesDataModel.setMovies(movies);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        moviesDataModel.setExpiredAt(calendar.getTime());

        return moviesDataModel;
    }
}
