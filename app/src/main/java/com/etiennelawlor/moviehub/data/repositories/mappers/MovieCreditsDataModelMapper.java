package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieCreditsDataModelMapper implements DataModelMapper<MovieCreditsEnvelope, MovieCreditsDataModel> {

    // region Constants
    private static final int SEVEN_DAYS = 30;
    // endregion

    @Override
    public MovieCreditsDataModel mapToDataModel(MovieCreditsEnvelope movieCreditsEnvelope) {
        MovieCreditsDataModel movieCreditsDataModel = new MovieCreditsDataModel();

        movieCreditsDataModel.setCast(movieCreditsEnvelope.getCast());
        movieCreditsDataModel.setCrew(movieCreditsEnvelope.getCrew());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        movieCreditsDataModel.setExpiredAt(calendar.getTime());
        movieCreditsDataModel.setId(movieCreditsEnvelope.getId());

        return movieCreditsDataModel;
    }
}
