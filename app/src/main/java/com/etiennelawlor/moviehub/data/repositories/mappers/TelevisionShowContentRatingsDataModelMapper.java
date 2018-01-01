package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowContentRatingsDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowContentRatingsDataModelMapper implements DataModelMapper<TelevisionShowContentRatingsResponse, TelevisionShowContentRatingsDataModel> {

    // region Member Variables
    private ContentRatingDataModelMapper contentRatingDataModelMapper = new ContentRatingDataModelMapper();
    // endregion

    @Override
    public TelevisionShowContentRatingsDataModel mapToDataModel(TelevisionShowContentRatingsResponse televisionShowContentRatingsResponse) {
        TelevisionShowContentRatingsDataModel televisionShowContentRatingsDataModel = new TelevisionShowContentRatingsDataModel();
        televisionShowContentRatingsDataModel.setContentRatings(contentRatingDataModelMapper.mapListToDataModelList(televisionShowContentRatingsResponse.getContentRatings()));
        televisionShowContentRatingsDataModel.setId(televisionShowContentRatingsResponse.getId());
        return televisionShowContentRatingsDataModel;
    }
}

