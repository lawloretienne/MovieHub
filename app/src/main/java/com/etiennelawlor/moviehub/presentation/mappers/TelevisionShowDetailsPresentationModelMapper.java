package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowDetailsPresentationModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class TelevisionShowDetailsPresentationModelMapper implements PresentationModelMapper<TelevisionShowDetailsDomainModel, TelevisionShowDetailsPresentationModel> {

    private TelevisionShowCreditPresentationModelMapper televisionShowCreditPresentationModelMapper = new TelevisionShowCreditPresentationModelMapper();
    private TelevisionShowPresentationModelMapper televisionShowPresentationModelMapper = new TelevisionShowPresentationModelMapper();

    @Override
    public TelevisionShowDetailsPresentationModel mapToPresentationModel(TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel) {
        TelevisionShowDetailsPresentationModel televisionShowDetailsPresentationModel = new TelevisionShowDetailsPresentationModel();
        televisionShowDetailsPresentationModel.setCast(televisionShowCreditPresentationModelMapper.mapListToPresentationModelList(televisionShowDetailsDomainModel.getCast()));
        televisionShowDetailsPresentationModel.setCrew(televisionShowCreditPresentationModelMapper.mapListToPresentationModelList(televisionShowDetailsDomainModel.getCrew()));
        televisionShowDetailsPresentationModel.setRating(televisionShowDetailsDomainModel.getRating());
        televisionShowDetailsPresentationModel.setSimilarTelevisionShows(televisionShowPresentationModelMapper.mapListToPresentationModelList(televisionShowDetailsDomainModel.getSimilarTelevisionShows()));
        televisionShowDetailsPresentationModel.setTelevisionShow(televisionShowPresentationModelMapper.mapToPresentationModel(televisionShowDetailsDomainModel.getTelevisionShow()));
        return televisionShowDetailsPresentationModel;
    }
}
