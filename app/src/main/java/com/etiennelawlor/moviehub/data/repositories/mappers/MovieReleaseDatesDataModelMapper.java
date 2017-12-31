package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesDataModelMapper implements DataModelMapper<MovieReleaseDatesEnvelope, MovieReleaseDatesDataModel> {

    // region Constants
    private static final int SEVEN_DAYS = 30;
    // endregion

    @Override
    public MovieReleaseDatesDataModel mapToDataModel(MovieReleaseDatesEnvelope movieReleaseDatesEnvelope) {
        MovieReleaseDatesDataModel movieReleaseDatesDataModel = new MovieReleaseDatesDataModel();

        movieReleaseDatesDataModel.setId(movieReleaseDatesEnvelope.getId());
        movieReleaseDatesDataModel.setMovieReleaseDateEnvelopes(movieReleaseDatesEnvelope.getMovieReleaseDateEnvelopes());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        movieReleaseDatesDataModel.setExpiredAt(calendar.getTime());

        return movieReleaseDatesDataModel;
    }
}
