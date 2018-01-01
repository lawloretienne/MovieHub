package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDatesDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieReleaseDatesDomainModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieReleaseDatesDomainModelMapper implements DomainModelMapper<MovieReleaseDatesDataModel, MovieReleaseDatesDomainModel> {

    // region Constants
    private static final int THIRTY_DAYS = 30;
    // endregion

    // region Member Variables
    private MovieReleaseDateDomainModelMapper movieReleaseDateDomainModelMapper = new MovieReleaseDateDomainModelMapper();
    // endregion

    @Override
    public MovieReleaseDatesDomainModel mapToDomainModel(MovieReleaseDatesDataModel movieReleaseDatesDataModel) {
        MovieReleaseDatesDomainModel movieReleaseDatesDomainModel = new MovieReleaseDatesDomainModel();
        movieReleaseDatesDomainModel.setId(movieReleaseDatesDataModel.getId());
        movieReleaseDatesDomainModel.setMovieReleaseDates(movieReleaseDateDomainModelMapper.mapListToDomainModelList(movieReleaseDatesDataModel.getMovieReleaseDates()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTY_DAYS);
        movieReleaseDatesDomainModel.setExpiredAt(calendar.getTime());
        return movieReleaseDatesDomainModel;
    }
}
