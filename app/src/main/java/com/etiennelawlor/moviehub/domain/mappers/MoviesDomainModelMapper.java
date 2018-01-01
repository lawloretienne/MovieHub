package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.domain.models.MoviesDomainModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MoviesDomainModelMapper implements DomainModelMapper<MoviesDataModel, MoviesDomainModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private MovieDomainModelMapper movieDomainModelMapper = new MovieDomainModelMapper();
    // endregion

    @Override
    public MoviesDomainModel mapToDomainModel(MoviesDataModel moviesDataModel) {
        MoviesDomainModel moviesDomainModel = new MoviesDomainModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        moviesDomainModel.setExpiredAt(calendar.getTime());
        moviesDomainModel.setLastPage(moviesDataModel.getMovies().size() < PAGE_SIZE ? true : false);
        moviesDomainModel.setMovies(movieDomainModelMapper.mapListToDomainModelList(moviesDataModel.getMovies()));
        moviesDomainModel.setPageNumber(moviesDataModel.getPageNumber());
        return moviesDomainModel;
    }
}
