package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MovieCreditsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MovieCreditsPresentationModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieCreditsPresentationModelMapper implements PresentationModelMapper<MovieCreditsDomainModel, MovieCreditsPresentationModel> {

    // region Constants
    private static final int THIRTY_DAYS = 30;
    // endregion

    // region Member Variables
    private MovieCreditPresentationModelMapper movieCreditPresentationModelMapper = new MovieCreditPresentationModelMapper();
    // endregion

    @Override
    public MovieCreditsPresentationModel mapToPresentationModel(MovieCreditsDomainModel movieCreditsDomainModel) {
        MovieCreditsPresentationModel movieCreditsPresentationModel = new MovieCreditsPresentationModel();
        movieCreditsPresentationModel.setCast(movieCreditPresentationModelMapper.mapListToPresentationModelList(movieCreditsDomainModel.getCast()));
        movieCreditsPresentationModel.setCrew(movieCreditPresentationModelMapper.mapListToPresentationModelList(movieCreditsDomainModel.getCrew()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTY_DAYS);
        movieCreditsPresentationModel.setExpiredAt(calendar.getTime());
        movieCreditsPresentationModel.setId(movieCreditsDomainModel.getId());
        return movieCreditsPresentationModel;
    }
}
