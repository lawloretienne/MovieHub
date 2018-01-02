package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.GenreDomainModel;
import com.etiennelawlor.moviehub.presentation.models.GenrePresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenrePresentationModelMapper implements PresentationModelMapper<GenreDomainModel, GenrePresentationModel>, PresentationModelListMapper<GenreDomainModel, GenrePresentationModel> {

    @Override
    public GenrePresentationModel mapToPresentationModel(GenreDomainModel genreDomainModel) {
        GenrePresentationModel genrePresentationModel = new GenrePresentationModel();
        genrePresentationModel.setId(genreDomainModel.getId());
        genrePresentationModel.setName(genreDomainModel.getName());
        return genrePresentationModel;
    }

    @Override
    public List<GenrePresentationModel> mapListToPresentationModelList(List<GenreDomainModel> genreDomainModels) {
        List<GenrePresentationModel> genrePresentationModels = new ArrayList<>();
        if(genreDomainModels != null && genreDomainModels.size()>0) {
            for (GenreDomainModel genreDomainModel : genreDomainModels) {
                genrePresentationModels.add(mapToPresentationModel(genreDomainModel));
            }
        }
        return genrePresentationModels;
    }
}
