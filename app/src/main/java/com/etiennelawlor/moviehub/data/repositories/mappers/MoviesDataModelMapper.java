package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MoviesDataModelMapper implements DataModelMapper<MoviesResponse, MoviesDataModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private MovieDataModelMapper movieDataModelMapper = new MovieDataModelMapper();
    // endregion

    @Override
    public MoviesDataModel mapToDataModel(MoviesResponse moviesResponse) {
        MoviesDataModel moviesDataModel = new MoviesDataModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        moviesDataModel.setExpiredAt(calendar.getTime());
        moviesDataModel.setLastPage(moviesResponse.getMovies().size() < PAGE_SIZE);
        moviesDataModel.setMovies(movieDataModelMapper.mapListToDataModelList(moviesResponse.getMovies()));
        moviesDataModel.setPageNumber(moviesResponse.getPage());
        return moviesDataModel;
    }
}
