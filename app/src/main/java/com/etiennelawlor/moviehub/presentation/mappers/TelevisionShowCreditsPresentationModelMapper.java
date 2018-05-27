package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowCreditsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowCreditsPresentationModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsPresentationModelMapper implements PresentationModelMapper<TelevisionShowCreditsDomainModel, TelevisionShowCreditsPresentationModel> {

    // region Member Variables
    private TelevisionShowCreditPresentationModelMapper televisionShowCreditPresentationModelMapper = new TelevisionShowCreditPresentationModelMapper();
    // endregion

    @Override
    public TelevisionShowCreditsPresentationModel mapToPresentationModel(TelevisionShowCreditsDomainModel televisionShowCreditsDomainModel) {
        TelevisionShowCreditsPresentationModel televisionShowCreditsPresentationModel = new TelevisionShowCreditsPresentationModel();
        televisionShowCreditsPresentationModel.setId(televisionShowCreditsDomainModel.getId());
        televisionShowCreditsPresentationModel.setCast(televisionShowCreditPresentationModelMapper.mapListToPresentationModelList(televisionShowCreditsDomainModel.getCast()));
        televisionShowCreditsPresentationModel.setCrew(televisionShowCreditPresentationModelMapper.mapListToPresentationModelList(televisionShowCreditsDomainModel.getCrew()));
        return televisionShowCreditsPresentationModel;
    }
}
