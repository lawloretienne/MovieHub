package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MovieCreditDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MovieCreditPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieCreditPresentationModelMapper implements PresentationModelMapper<MovieCreditDomainModel, MovieCreditPresentationModel>, PresentationModelListMapper<MovieCreditDomainModel, MovieCreditPresentationModel> {

    @Override
    public MovieCreditPresentationModel mapToPresentationModel(MovieCreditDomainModel movieCreditDomainModel) {
        MovieCreditPresentationModel movieCreditPresentationModel = new MovieCreditPresentationModel();
        movieCreditPresentationModel.setCharacter(movieCreditDomainModel.getCharacter());
        movieCreditPresentationModel.setDepartment(movieCreditDomainModel.getDepartment());
        movieCreditPresentationModel.setJob(movieCreditDomainModel.getJob());
        movieCreditPresentationModel.setName(movieCreditDomainModel.getName());
        movieCreditPresentationModel.setProfilePath(movieCreditDomainModel.getProfilePath());
        movieCreditPresentationModel.setCreditId(movieCreditDomainModel.getCreditId());
        movieCreditPresentationModel.setId(movieCreditDomainModel.getId());
        return movieCreditPresentationModel;
    }

    @Override
    public List<MovieCreditPresentationModel> mapListToPresentationModelList(List<MovieCreditDomainModel> movieCreditDomainModels) {
        List<MovieCreditPresentationModel> movieCreditPresentationModels = new ArrayList<>();
        if(movieCreditDomainModels != null && movieCreditDomainModels.size()>0) {
            for (MovieCreditDomainModel movieCreditDomainModel : movieCreditDomainModels) {
                movieCreditPresentationModels.add(mapToPresentationModel(movieCreditDomainModel));
            }
        }
        return movieCreditPresentationModels;
    }
}
