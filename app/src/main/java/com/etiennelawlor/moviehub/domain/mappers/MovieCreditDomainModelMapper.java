package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.MovieCreditDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieCreditDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieCreditDomainModelMapper implements DomainModelMapper<MovieCreditDataModel, MovieCreditDomainModel>, DomainModelListMapper<MovieCreditDataModel, MovieCreditDomainModel> {

    @Override
    public MovieCreditDomainModel mapToDomainModel(MovieCreditDataModel movieCreditDataModel) {
        MovieCreditDomainModel movieCreditDomainModel = new MovieCreditDomainModel();
        movieCreditDomainModel.setCharacter(movieCreditDataModel.getCharacter());
        movieCreditDomainModel.setDepartment(movieCreditDataModel.getDepartment());
        movieCreditDomainModel.setJob(movieCreditDataModel.getJob());
        movieCreditDomainModel.setName(movieCreditDataModel.getName());
        movieCreditDomainModel.setProfilePath(movieCreditDataModel.getProfilePath());
        movieCreditDomainModel.setCreditId(movieCreditDataModel.getCreditId());
        movieCreditDomainModel.setId(movieCreditDataModel.getId());
        return movieCreditDomainModel;
    }

    @Override
    public List<MovieCreditDomainModel> mapListToDomainModelList(List<MovieCreditDataModel> movieCreditDataModels) {
        List<MovieCreditDomainModel> movieCreditDomainModels = new ArrayList<>();
        if(movieCreditDataModels != null && movieCreditDataModels.size()>0) {
            for (MovieCreditDataModel movieCreditDataModel : movieCreditDataModels) {
                movieCreditDomainModels.add(mapToDomainModel(movieCreditDataModel));
            }
        }
        return movieCreditDomainModels;
    }
}
