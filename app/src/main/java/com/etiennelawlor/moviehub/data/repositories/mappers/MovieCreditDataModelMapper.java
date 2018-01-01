package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditDataModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieCreditDataModelMapper implements DataModelMapper<MovieCreditResponse, MovieCreditDataModel> {

    @Override
    public MovieCreditDataModel mapToDataModel(MovieCreditResponse movieCreditResponse) {
        MovieCreditDataModel movieCreditDataModel = new MovieCreditDataModel();
        movieCreditDataModel.setCharacter(movieCreditResponse.getCharacter());
        movieCreditDataModel.setDepartment(movieCreditResponse.getDepartment());
        movieCreditDataModel.setJob(movieCreditResponse.getJob());
        movieCreditDataModel.setName(movieCreditResponse.getName());
        movieCreditDataModel.setProfilePalette(movieCreditResponse.getProfilePalette());
        movieCreditDataModel.setProfilePath(movieCreditResponse.getProfilePath());
        movieCreditDataModel.setCreditId(movieCreditResponse.getCreditId());
        movieCreditDataModel.setId(movieCreditResponse.getId());

        return movieCreditDataModel;
    }
}
