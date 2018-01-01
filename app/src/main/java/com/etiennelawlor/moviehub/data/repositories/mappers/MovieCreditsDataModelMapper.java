package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieCreditResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditsDataModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieCreditsDataModelMapper implements DataModelMapper<MovieCreditsResponse, MovieCreditsDataModel> {

    // region Constants
    private static final int SEVEN_DAYS = 30;
    // endregion

    // region Member Variables
    private MovieCreditDataModelMapper movieCreditDataModelMapper = new MovieCreditDataModelMapper();
    // endregion

    @Override
    public MovieCreditsDataModel mapToDataModel(MovieCreditsResponse movieCreditsResponse) {
        MovieCreditsDataModel movieCreditsDataModel = new MovieCreditsDataModel();

        List<MovieCreditResponse> castMovieCreditResponses = movieCreditsResponse.getCast();
        List<MovieCreditDataModel> castMovieCreditDataModels = new ArrayList<>();
        if(castMovieCreditResponses != null && castMovieCreditResponses.size()>0) {
            for (MovieCreditResponse movieCreditResponse : castMovieCreditResponses) {
                castMovieCreditDataModels.add(movieCreditDataModelMapper.mapToDataModel(movieCreditResponse));
            }
        }
        movieCreditsDataModel.setCast(castMovieCreditDataModels);

        List<MovieCreditResponse> crewMovieCreditResponses = movieCreditsResponse.getCrew();
        List<MovieCreditDataModel> crewMovieCreditDataModels = new ArrayList<>();
        if(crewMovieCreditResponses != null && crewMovieCreditResponses.size()>0) {
            for (MovieCreditResponse movieCreditResponse : crewMovieCreditResponses) {
                crewMovieCreditDataModels.add(movieCreditDataModelMapper.mapToDataModel(movieCreditResponse));
            }
        }
        movieCreditsDataModel.setCrew(crewMovieCreditDataModels);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        movieCreditsDataModel.setExpiredAt(calendar.getTime());
        movieCreditsDataModel.setId(movieCreditsResponse.getId());

        return movieCreditsDataModel;
    }
}
