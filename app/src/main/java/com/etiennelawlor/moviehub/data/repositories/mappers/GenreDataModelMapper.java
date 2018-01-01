package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.GenreResponse;
import com.etiennelawlor.moviehub.data.repositories.models.GenreDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenreDataModelMapper implements DataModelMapper<GenreResponse, GenreDataModel> {

    @Override
    public GenreDataModel mapToDataModel(GenreResponse genreResponse) {
        GenreDataModel genreDataModel = new GenreDataModel();
        genreDataModel.setId(genreResponse.getId());
        genreDataModel.setName(genreResponse.getName());
        return genreDataModel;
    }
}
