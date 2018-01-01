package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowCreditsDomainModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsDomainModelMapper implements DomainModelMapper<TelevisionShowCreditsDataModel, TelevisionShowCreditsDomainModel> {

    // region Member Variables
    private TelevisionShowCreditDomainModelMapper televisionShowCreditDomainModelMapper = new TelevisionShowCreditDomainModelMapper();
    // endregion

    @Override
    public TelevisionShowCreditsDomainModel mapToDomainModel(TelevisionShowCreditsDataModel televisionShowCreditsDataModel) {
        TelevisionShowCreditsDomainModel televisionShowCreditsDomainModel = new TelevisionShowCreditsDomainModel();
        televisionShowCreditsDomainModel.setId(televisionShowCreditsDataModel.getId());
        televisionShowCreditsDomainModel.setCast(televisionShowCreditDomainModelMapper.mapListToDomainModelList(televisionShowCreditsDataModel.getCast()));
        televisionShowCreditsDomainModel.setCrew(televisionShowCreditDomainModelMapper.mapListToDomainModelList(televisionShowCreditsDataModel.getCrew()));
        return televisionShowCreditsDomainModel;
    }
}
