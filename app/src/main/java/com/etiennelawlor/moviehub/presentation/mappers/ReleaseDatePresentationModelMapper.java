package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.ReleaseDateDomainModel;
import com.etiennelawlor.moviehub.presentation.models.ReleaseDatePresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class ReleaseDatePresentationModelMapper implements PresentationModelMapper<ReleaseDateDomainModel, ReleaseDatePresentationModel>, PresentationModelListMapper<ReleaseDateDomainModel, ReleaseDatePresentationModel> {

    @Override
    public ReleaseDatePresentationModel mapToPresentationModel(ReleaseDateDomainModel releaseDateDomainModel) {
        ReleaseDatePresentationModel releaseDatePresentationModel = new ReleaseDatePresentationModel();
        releaseDatePresentationModel.setCertification(releaseDateDomainModel.getCertification());
        releaseDatePresentationModel.setIso6391(releaseDateDomainModel.getIso6391());
        releaseDatePresentationModel.setNote(releaseDateDomainModel.getNote());
        releaseDatePresentationModel.setReleaseDate(releaseDateDomainModel.getReleaseDate());
        releaseDatePresentationModel.setType(releaseDateDomainModel.getType());
        return releaseDatePresentationModel;
    }

    @Override
    public List<ReleaseDatePresentationModel> mapListToPresentationModelList(List<ReleaseDateDomainModel> releaseDateDomainModels) {
        List<ReleaseDatePresentationModel> releaseDatePresentationModels = new ArrayList<>();
        if(releaseDateDomainModels != null && releaseDateDomainModels.size()>0) {
            for (ReleaseDateDomainModel releaseDateDomainModel : releaseDateDomainModels) {
                releaseDatePresentationModels.add(mapToPresentationModel(releaseDateDomainModel));
            }
        }
        return releaseDatePresentationModels;
    }
}
