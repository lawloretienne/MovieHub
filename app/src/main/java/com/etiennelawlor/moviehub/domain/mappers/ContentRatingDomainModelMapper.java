package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.ContentRatingDataModel;
import com.etiennelawlor.moviehub.domain.models.ContentRatingDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ContentRatingDomainModelMapper implements DomainModelMapper<ContentRatingDataModel, ContentRatingDomainModel>, DomainModelListMapper<ContentRatingDataModel, ContentRatingDomainModel> {

    @Override
    public ContentRatingDomainModel mapToDomainModel(ContentRatingDataModel contentRatingDataModel) {
        ContentRatingDomainModel contentRatingDomainModel = new ContentRatingDomainModel();
        contentRatingDomainModel.setIso31661(contentRatingDataModel.getIso31661());
        contentRatingDomainModel.setRating(contentRatingDataModel.getRating());
        return contentRatingDomainModel;
    }

    @Override
    public List<ContentRatingDomainModel> mapListToDomainModelList(List<ContentRatingDataModel> contentRatingDataModels) {
        List<ContentRatingDomainModel> contentRatingDomainModels = new ArrayList<>();
        if(contentRatingDataModels != null && contentRatingDataModels.size()>0) {
            for (ContentRatingDataModel contentRatingDataModel : contentRatingDataModels) {
                contentRatingDomainModels.add(mapToDomainModel(contentRatingDataModel));
            }
        }
        return contentRatingDomainModels;
    }
}

