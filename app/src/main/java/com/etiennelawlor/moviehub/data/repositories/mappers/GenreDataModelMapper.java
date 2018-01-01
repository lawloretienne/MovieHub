package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.GenreResponse;
import com.etiennelawlor.moviehub.data.repositories.models.GenreDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenreDataModelMapper implements DataModelMapper<GenreResponse, GenreDataModel>, DataModelListMapper<GenreResponse, GenreDataModel>{

    @Override
    public GenreDataModel mapToDataModel(GenreResponse genreResponse) {
        GenreDataModel genreDataModel = new GenreDataModel();
        genreDataModel.setId(genreResponse.getId());
        genreDataModel.setName(genreResponse.getName());
        return genreDataModel;
    }

    @Override
    public List<GenreDataModel> mapListToDataModelList(List<GenreResponse> genreResponses) {
        List<GenreDataModel> genreDataModels = new ArrayList<>();
        if(genreResponses != null && genreResponses.size()>0) {
            for (GenreResponse genreResponse : genreResponses) {
                genreDataModels.add(mapToDataModel(genreResponse));
            }
        }
        return genreDataModels;
    }
}
