package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.ReleaseDateDataModel;
import com.etiennelawlor.moviehub.domain.models.ReleaseDateDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class ReleaseDateDomainModelMapper implements DomainModelMapper<ReleaseDateDataModel, ReleaseDateDomainModel>, DomainModelListMapper<ReleaseDateDataModel, ReleaseDateDomainModel> {

    @Override
    public ReleaseDateDomainModel mapToDomainModel(ReleaseDateDataModel releaseDateDataModel) {
        ReleaseDateDomainModel releaseDateDomainModel = new ReleaseDateDomainModel();
        releaseDateDomainModel.setCertification(releaseDateDataModel.getCertification());
        releaseDateDomainModel.setIso6391(releaseDateDataModel.getIso6391());
        releaseDateDomainModel.setNote(releaseDateDataModel.getNote());
        releaseDateDomainModel.setReleaseDate(releaseDateDataModel.getReleaseDate());
        releaseDateDomainModel.setType(releaseDateDataModel.getType());
        return releaseDateDomainModel;
    }

    @Override
    public List<ReleaseDateDomainModel> mapListToDomainModelList(List<ReleaseDateDataModel> releaseDateDataModels) {
        List<ReleaseDateDomainModel> releaseDateDomainModels = new ArrayList<>();
        if(releaseDateDataModels != null && releaseDateDataModels.size()>0) {
            for (ReleaseDateDataModel releaseDateDataModel : releaseDateDataModels) {
                releaseDateDomainModels.add(mapToDomainModel(releaseDateDataModel));
            }
        }
        return releaseDateDomainModels;
    }
}
