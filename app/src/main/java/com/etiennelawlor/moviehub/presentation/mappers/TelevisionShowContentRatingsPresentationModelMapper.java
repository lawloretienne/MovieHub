package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowContentRatingsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowContentRatingsPresentationModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowContentRatingsPresentationModelMapper implements PresentationModelMapper<TelevisionShowContentRatingsDomainModel, TelevisionShowContentRatingsPresentationModel> {

    // region Member Variables
    private ContentRatingPresentationModelMapper contentRatingPresentationModelMapper = new ContentRatingPresentationModelMapper();
    // endregion

    @Override
    public TelevisionShowContentRatingsPresentationModel mapToPresentationModel(TelevisionShowContentRatingsDomainModel televisionShowContentRatingsDomainModel) {
        TelevisionShowContentRatingsPresentationModel televisionShowContentRatingsPresentationModel = new TelevisionShowContentRatingsPresentationModel();
        televisionShowContentRatingsPresentationModel.setContentRatings(contentRatingPresentationModelMapper.mapListToPresentationModelList(televisionShowContentRatingsDomainModel.getContentRatings()));
        televisionShowContentRatingsPresentationModel.setId(televisionShowContentRatingsDomainModel.getId());
        return televisionShowContentRatingsPresentationModel;
    }
}

