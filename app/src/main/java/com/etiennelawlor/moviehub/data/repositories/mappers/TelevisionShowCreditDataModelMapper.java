package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsDataModelMapper implements DataModelMapper<TelevisionShowCreditsResponse, TelevisionShowCreditsDataModel> {

    @Override
    public TelevisionShowCreditsDataModel mapToDataModel(TelevisionShowCreditsResponse televisionShowCreditsResponse) {
        TelevisionShowCreditsDataModel televisionShowCreditsDataModel = new TelevisionShowCreditsDataModel();
        televisionShowCreditsDataModel.setId(televisionShowCreditsResponse.getId());
        televisionShowCreditsDataModel.setCast(televisionShowCreditsResponse.getCast());
        televisionShowCreditsDataModel.setCrew(televisionShowCreditsResponse.getCrew());
        return null;
    }
}
