package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDateResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDateDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

        List<MovieReleaseDateResponse> movieReleaseDateResponses = movieReleaseDatesResponse.getMovieReleaseDates();
        List<MovieReleaseDateDataModel> movieReleaseDateDataModels = new ArrayList<>();
        if(movieReleaseDateResponses != null && movieReleaseDateResponses.size()>0) {
            for (MovieReleaseDateResponse movieReleaseDateResponse : movieReleaseDateResponses) {
                movieReleaseDateDataModels.add(movieReleaseDateDataModelMapper.mapToDataModel(movieReleaseDateResponse));
            }
        }
        movieReleaseDatesDataModel.setMovieReleaseDates(movieReleaseDateDataModels);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTY_DAYS);
        movieReleaseDatesDataModel.setExpiredAt(calendar.getTime());

        return movieReleaseDatesDataModel;
    }
}
