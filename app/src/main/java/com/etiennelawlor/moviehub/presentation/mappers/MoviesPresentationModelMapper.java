package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MoviesDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MoviesPresentationModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MoviesPresentationModelMapper implements PresentationModelMapper<MoviesDomainModel, MoviesPresentationModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private MoviePresentationModelMapper moviePresentationModelMapper = new MoviePresentationModelMapper();
    // endregion

    @Override
    public MoviesPresentationModel mapToPresentationModel(MoviesDomainModel moviesDomainModel) {
        MoviesPresentationModel moviesPresentationModel = new MoviesPresentationModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        moviesPresentationModel.setExpiredAt(calendar.getTime());
        moviesPresentationModel.setLastPage(moviesDomainModel.getMovies().size() < PAGE_SIZE);
        moviesPresentationModel.setMovies(moviePresentationModelMapper.mapListToPresentationModelList(moviesDomainModel.getMovies()));
        moviesPresentationModel.setPageNumber(moviesDomainModel.getPageNumber());
        return moviesPresentationModel;
    }
}
