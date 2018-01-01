package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.GenreDataModel;
import com.etiennelawlor.moviehub.domain.models.GenreDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenreDomainModelMapper implements DomainModelMapper<GenreDataModel, GenreDomainModel>, DomainModelListMapper<GenreDataModel, GenreDomainModel> {

    @Override
    public GenreDomainModel mapToDomainModel(GenreDataModel genreDataModel) {
        GenreDomainModel genreDomainModel = new GenreDomainModel();
        genreDomainModel.setId(genreDataModel.getId());
        genreDomainModel.setName(genreDataModel.getName());
        return genreDomainModel;
    }

    @Override
    public List<GenreDomainModel> mapListToDomainModelList(List<GenreDataModel> genreDataModels) {
        List<GenreDomainModel> genreDomainModels = new ArrayList<>();
        if(genreDataModels != null && genreDataModels.size()>0) {
            for (GenreDataModel genreDataModel : genreDataModels) {
                genreDomainModels.add(mapToDomainModel(genreDataModel));
            }
        }
        return genreDomainModels;
    }
}
