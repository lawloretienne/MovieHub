package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowContentRatingsDataModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowContentRatingsDomainModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowContentRatingsDomainModelMapper implements DomainModelMapper<TelevisionShowContentRatingsDataModel, TelevisionShowContentRatingsDomainModel> {

    // region Member Variables
    private ContentRatingDomainModelMapper contentRatingDomainModelMapper = new ContentRatingDomainModelMapper();
    // endregion

    @Override
    public TelevisionShowContentRatingsDomainModel mapToDomainModel(TelevisionShowContentRatingsDataModel televisionShowContentRatingsDataModel) {
        TelevisionShowContentRatingsDomainModel televisionShowContentRatingsDomainModel = new TelevisionShowContentRatingsDomainModel();
        televisionShowContentRatingsDomainModel.setContentRatings(contentRatingDomainModelMapper.mapListToDomainModelList(televisionShowContentRatingsDataModel.getContentRatings()));
        televisionShowContentRatingsDomainModel.setId(televisionShowContentRatingsDataModel.getId());
        return televisionShowContentRatingsDomainModel;
    }
}

