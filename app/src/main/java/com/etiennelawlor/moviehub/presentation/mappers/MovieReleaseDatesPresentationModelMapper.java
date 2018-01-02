package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MovieReleaseDatesDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MovieReleaseDatesPresentationModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesPresentationModelMapper implements PresentationModelMapper<MovieReleaseDatesDomainModel, MovieReleaseDatesPresentationModel> {

    // region Constants
    private static final int THIRTY_DAYS = 30;
    // endregion

    // region Member Variables
    private MovieReleaseDatePresentationModelMapper movieReleaseDatePresentationModelMapper = new MovieReleaseDatePresentationModelMapper();
    // endregion

    @Override
    public MovieReleaseDatesPresentationModel mapToPresentationModel(MovieReleaseDatesDomainModel movieReleaseDatesDomainModel) {
        MovieReleaseDatesPresentationModel movieReleaseDatesPresentationModel = new MovieReleaseDatesPresentationModel();
        movieReleaseDatesPresentationModel.setId(movieReleaseDatesDomainModel.getId());
        movieReleaseDatesPresentationModel.setMovieReleaseDates(movieReleaseDatePresentationModelMapper.mapListToPresentationModelList(movieReleaseDatesDomainModel.getMovieReleaseDates()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTY_DAYS);
        movieReleaseDatesPresentationModel.setExpiredAt(calendar.getTime());
        return movieReleaseDatesPresentationModel;
    }
}
