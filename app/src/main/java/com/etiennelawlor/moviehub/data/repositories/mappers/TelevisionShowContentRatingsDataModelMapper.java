package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ContentRatingResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ContentRatingDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowContentRatingsDataModel;

import java.util.ArrayList;
import java.util.List;

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

        List<ContentRatingResponse> contentRatingResponses = televisionShowContentRatingsResponse.getContentRatings();
        List<ContentRatingDataModel> contentRatingDataModels = new ArrayList<>();
        if(contentRatingResponses != null && contentRatingResponses.size()>0) {
            for (ContentRatingResponse contentRatingResponse : contentRatingResponses) {
                contentRatingDataModels.add(contentRatingDataModelMapper.mapToDataModel(contentRatingResponse));
            }
        }
        televisionShowContentRatingsDataModel.setContentRatings(contentRatingDataModels);

        televisionShowContentRatingsDataModel.setId(televisionShowContentRatingsResponse.getId());
        return televisionShowContentRatingsDataModel;
    }
}

