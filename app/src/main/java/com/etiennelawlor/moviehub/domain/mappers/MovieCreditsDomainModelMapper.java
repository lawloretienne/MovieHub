package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieCreditsDomainModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieCreditsDomainModelMapper implements DomainModelMapper<MovieCreditsDataModel, MovieCreditsDomainModel> {

    // region Constants
    private static final int THIRTY_DAYS = 30;
    // endregion

    // region Member Variables
    private MovieCreditDomainModelMapper movieCreditDomainModelMapper = new MovieCreditDomainModelMapper();
    // endregion

    @Override
    public MovieCreditsDomainModel mapToDomainModel(MovieCreditsDataModel movieCreditsDataModel) {
        MovieCreditsDomainModel movieCreditsDomainModel = new MovieCreditsDomainModel();
        movieCreditsDomainModel.setCast(movieCreditDomainModelMapper.mapListToDomainModelList(movieCreditsDataModel.getCast()));
        movieCreditsDomainModel.setCrew(movieCreditDomainModelMapper.mapListToDomainModelList(movieCreditsDataModel.getCrew()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTY_DAYS);
        movieCreditsDomainModel.setExpiredAt(calendar.getTime());
        movieCreditsDomainModel.setId(movieCreditsDataModel.getId());
        return movieCreditsDomainModel;
    }
}
