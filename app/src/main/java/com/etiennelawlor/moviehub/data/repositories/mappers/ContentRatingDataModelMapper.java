package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ContentRatingResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ContentRatingDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ContentRatingDataModelMapper implements DataModelMapper<ContentRatingResponse, ContentRatingDataModel> {

    @Override
    public ContentRatingDataModel mapToDataModel(ContentRatingResponse contentRatingResponse) {
        ContentRatingDataModel contentRatingDataModel = new ContentRatingDataModel();
        contentRatingDataModel.setIso31661(contentRatingResponse.getIso31661());
        contentRatingDataModel.setRating(contentRatingResponse.getRating());
        return contentRatingDataModel;
    }
}

