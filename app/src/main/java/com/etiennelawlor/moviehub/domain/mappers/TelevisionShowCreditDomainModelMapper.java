package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditDataModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowCreditDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditDomainModelMapper implements DomainModelMapper<TelevisionShowCreditDataModel, TelevisionShowCreditDomainModel>, DomainModelListMapper<TelevisionShowCreditDataModel, TelevisionShowCreditDomainModel> {

    @Override
    public TelevisionShowCreditDomainModel mapToDomainModel(TelevisionShowCreditDataModel televisionShowCreditDataModel) {
        TelevisionShowCreditDomainModel televisionShowCreditDomainModel = new TelevisionShowCreditDomainModel();
        televisionShowCreditDomainModel.setCharacter(televisionShowCreditDataModel.getCharacter());
        televisionShowCreditDomainModel.setDepartment(televisionShowCreditDataModel.getDepartment());
        televisionShowCreditDomainModel.setJob(televisionShowCreditDataModel.getJob());
        televisionShowCreditDomainModel.setName(televisionShowCreditDataModel.getName());
        televisionShowCreditDomainModel.setProfilePath(televisionShowCreditDataModel.getProfilePath());
        televisionShowCreditDomainModel.setCreditId(televisionShowCreditDataModel.getCreditId());
        televisionShowCreditDomainModel.setId(televisionShowCreditDataModel.getId());
        return televisionShowCreditDomainModel;
    }

    @Override
    public List<TelevisionShowCreditDomainModel> mapListToDomainModelList(List<TelevisionShowCreditDataModel> televisionShowCreditDataModels) {
        List<TelevisionShowCreditDomainModel> televisionShowCreditDomainModels = new ArrayList<>();
        if(televisionShowCreditDataModels != null && televisionShowCreditDataModels.size()>0) {
            for (TelevisionShowCreditDataModel televisionShowCreditDataModel : televisionShowCreditDataModels) {
                televisionShowCreditDomainModels.add(mapToDomainModel(televisionShowCreditDataModel));
            }
        }
        return televisionShowCreditDomainModels;
    }
}
