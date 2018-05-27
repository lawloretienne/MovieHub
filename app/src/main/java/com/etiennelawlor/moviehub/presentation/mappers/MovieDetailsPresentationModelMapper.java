package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MovieDetailsPresentationModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieDetailsPresentationModelMapper implements PresentationModelMapper<MovieDetailsDomainModel, MovieDetailsPresentationModel> {

    // region Member Variables
    private MovieCreditPresentationModelMapper movieCreditPresentationModelMapper = new MovieCreditPresentationModelMapper();
    private MoviePresentationModelMapper moviePresentationModelMapper = new MoviePresentationModelMapper();
    // endregion

    @Override
    public MovieDetailsPresentationModel mapToPresentationModel(MovieDetailsDomainModel movieDetailsDomainModel) {
        MovieDetailsPresentationModel movieDetailsPresentationModel = new MovieDetailsPresentationModel();
        movieDetailsPresentationModel.setCast(movieCreditPresentationModelMapper.mapListToPresentationModelList(movieDetailsDomainModel.getCast()));
        movieDetailsPresentationModel.setCrew(movieCreditPresentationModelMapper.mapListToPresentationModelList(movieDetailsDomainModel.getCrew()));
        movieDetailsPresentationModel.setMovie(moviePresentationModelMapper.mapToPresentationModel(movieDetailsDomainModel.getMovie()));
        movieDetailsPresentationModel.setRating(movieDetailsDomainModel.getRating());
        movieDetailsPresentationModel.setSimilarMovies(moviePresentationModelMapper.mapListToPresentationModelList(movieDetailsDomainModel.getSimilarMovies()));
        return movieDetailsPresentationModel;
    }
}
