package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsDataModelMapper implements DataModelMapper<TelevisionShowCreditsResponse, TelevisionShowCreditsDataModel> {

    // region Member Variables
    private TelevisionShowCreditDataModelMapper televisionShowCreditDataModelMapper = new TelevisionShowCreditDataModelMapper();
    // endregion

    @Override
    public TelevisionShowCreditsDataModel mapToDataModel(TelevisionShowCreditsResponse televisionShowCreditsResponse) {
        TelevisionShowCreditsDataModel televisionShowCreditsDataModel = new TelevisionShowCreditsDataModel();
        televisionShowCreditsDataModel.setId(televisionShowCreditsResponse.getId());
        televisionShowCreditsDataModel.setCast(televisionShowCreditDataModelMapper.mapListToDataModelList(televisionShowCreditsResponse.getCast()));
        televisionShowCreditsDataModel.setCrew(televisionShowCreditDataModelMapper.mapListToDataModelList(televisionShowCreditsResponse.getCrew()));
        return televisionShowCreditsDataModel;
    }
}
