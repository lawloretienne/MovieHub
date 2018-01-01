package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesDataModelMapper implements DataModelMapper<MovieReleaseDatesResponse, MovieReleaseDatesDataModel> {

    // region Constants
    private static final int THIRTY_DAYS = 30;
    // endregion

    // region Member Variables
    private MovieReleaseDateDataModelMapper movieReleaseDateDataModelMapper = new MovieReleaseDateDataModelMapper();
    // endregion

    @Override
    public MovieReleaseDatesDataModel mapToDataModel(MovieReleaseDatesResponse movieReleaseDatesResponse) {
        MovieReleaseDatesDataModel movieReleaseDatesDataModel = new MovieReleaseDatesDataModel();
        movieReleaseDatesDataModel.setId(movieReleaseDatesResponse.getId());
        movieReleaseDatesDataModel.setMovieReleaseDates(movieReleaseDateDataModelMapper.mapListToDataModelList(movieReleaseDatesResponse.getMovieReleaseDates()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTY_DAYS);
        movieReleaseDatesDataModel.setExpiredAt(calendar.getTime());
        return movieReleaseDatesDataModel;
    }
}
