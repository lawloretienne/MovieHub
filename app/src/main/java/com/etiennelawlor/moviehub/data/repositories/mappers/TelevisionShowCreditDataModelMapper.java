package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditDataModelMapper implements DataModelMapper<TelevisionShowCreditResponse, TelevisionShowCreditDataModel> {

    @Override
    public TelevisionShowCreditDataModel mapToDataModel(TelevisionShowCreditResponse televisionShowCreditResponse) {
        TelevisionShowCreditDataModel televisionShowCreditDataModel = new TelevisionShowCreditDataModel();
        televisionShowCreditDataModel.setCharacter(televisionShowCreditResponse.getCharacter());
        televisionShowCreditDataModel.setDepartment(televisionShowCreditResponse.getDepartment());
        televisionShowCreditDataModel.setJob(televisionShowCreditResponse.getJob());
        televisionShowCreditDataModel.setName(televisionShowCreditResponse.getName());
        televisionShowCreditDataModel.setProfilePalette(televisionShowCreditResponse.getProfilePalette());
        televisionShowCreditDataModel.setProfilePath(televisionShowCreditResponse.getProfilePath());
        televisionShowCreditDataModel.setCreditId(televisionShowCreditResponse.getCreditId());
        televisionShowCreditDataModel.setId(televisionShowCreditResponse.getId());

        return televisionShowCreditDataModel;
    }
}
