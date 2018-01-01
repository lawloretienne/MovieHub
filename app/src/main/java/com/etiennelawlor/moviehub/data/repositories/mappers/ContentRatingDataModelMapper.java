package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ContentRatingResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ContentRatingDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ContentRatingDataModelMapper implements DataModelMapper<ContentRatingResponse, ContentRatingDataModel>, DataModelListMapper<ContentRatingResponse, ContentRatingDataModel> {

    @Override
    public ContentRatingDataModel mapToDataModel(ContentRatingResponse contentRatingResponse) {
        ContentRatingDataModel contentRatingDataModel = new ContentRatingDataModel();
        contentRatingDataModel.setIso31661(contentRatingResponse.getIso31661());
        contentRatingDataModel.setRating(contentRatingResponse.getRating());
        return contentRatingDataModel;
    }

    @Override
    public List<ContentRatingDataModel> mapListToDataModelList(List<ContentRatingResponse> contentRatingResponses) {
        List<ContentRatingDataModel> contentRatingDataModels = new ArrayList<>();
        if(contentRatingResponses != null && contentRatingResponses.size()>0) {
            for (ContentRatingResponse contentRatingResponse : contentRatingResponses) {
                contentRatingDataModels.add(mapToDataModel(contentRatingResponse));
            }
        }
        return contentRatingDataModels;
    }
}

