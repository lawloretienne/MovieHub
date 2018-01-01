package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ReleaseDateResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ReleaseDateDataModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class ReleaseDateDataModelMapper implements DataModelMapper<ReleaseDateResponse, ReleaseDateDataModel> {

    @Override
    public ReleaseDateDataModel mapToDataModel(ReleaseDateResponse releaseDateResponse) {
        ReleaseDateDataModel releaseDateDataModel = new ReleaseDateDataModel();
        releaseDateDataModel.setCertification(releaseDateResponse.getCertification());
        releaseDateDataModel.setIso6391(releaseDateResponse.getIso6391());
        releaseDateDataModel.setNote(releaseDateResponse.getNote());
        releaseDateDataModel.setReleaseDate(releaseDateResponse.getReleaseDate());
        releaseDateDataModel.setType(releaseDateResponse.getType());

        return releaseDateDataModel;
    }
}
