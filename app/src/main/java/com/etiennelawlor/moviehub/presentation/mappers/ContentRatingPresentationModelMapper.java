package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.ContentRatingDomainModel;
import com.etiennelawlor.moviehub.presentation.models.ContentRatingPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ContentRatingPresentationModelMapper implements PresentationModelMapper<ContentRatingDomainModel, ContentRatingPresentationModel>, PresentationModelListMapper<ContentRatingDomainModel, ContentRatingPresentationModel> {

    @Override
    public ContentRatingPresentationModel mapToPresentationModel(ContentRatingDomainModel contentRatingDomainModel) {
        ContentRatingPresentationModel contentRatingPresentationModel = new ContentRatingPresentationModel();
        contentRatingPresentationModel.setIso31661(contentRatingDomainModel.getIso31661());
        contentRatingPresentationModel.setRating(contentRatingDomainModel.getRating());
        return contentRatingPresentationModel;
    }

    @Override
    public List<ContentRatingPresentationModel> mapListToPresentationModelList(List<ContentRatingDomainModel> contentRatingDomainModels) {
        List<ContentRatingPresentationModel> contentRatingPresentationModels = new ArrayList<>();
        if(contentRatingDomainModels != null && contentRatingDomainModels.size()>0) {
            for (ContentRatingDomainModel contentRatingDomainModel : contentRatingDomainModels) {
                contentRatingPresentationModels.add(mapToPresentationModel(contentRatingDomainModel));
            }
        }
        return contentRatingPresentationModels;
    }
}

