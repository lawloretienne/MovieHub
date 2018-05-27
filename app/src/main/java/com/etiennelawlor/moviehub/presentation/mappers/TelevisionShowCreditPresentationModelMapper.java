package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowCreditDomainModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowCreditPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditPresentationModelMapper implements PresentationModelMapper<TelevisionShowCreditDomainModel, TelevisionShowCreditPresentationModel>, PresentationModelListMapper<TelevisionShowCreditDomainModel, TelevisionShowCreditPresentationModel> {

    @Override
    public TelevisionShowCreditPresentationModel mapToPresentationModel(TelevisionShowCreditDomainModel televisionShowCreditDomainModel) {
        TelevisionShowCreditPresentationModel televisionShowCreditPresentationModel = new TelevisionShowCreditPresentationModel();
        televisionShowCreditPresentationModel.setCharacter(televisionShowCreditDomainModel.getCharacter());
        televisionShowCreditPresentationModel.setDepartment(televisionShowCreditDomainModel.getDepartment());
        televisionShowCreditPresentationModel.setJob(televisionShowCreditDomainModel.getJob());
        televisionShowCreditPresentationModel.setName(televisionShowCreditDomainModel.getName());
        televisionShowCreditPresentationModel.setProfilePath(televisionShowCreditDomainModel.getProfilePath());
        televisionShowCreditPresentationModel.setCreditId(televisionShowCreditDomainModel.getCreditId());
        televisionShowCreditPresentationModel.setId(televisionShowCreditDomainModel.getId());
        return televisionShowCreditPresentationModel;
    }

    @Override
    public List<TelevisionShowCreditPresentationModel> mapListToPresentationModelList(List<TelevisionShowCreditDomainModel> televisionShowCreditDomainModels) {
        List<TelevisionShowCreditPresentationModel> televisionShowCreditPresentationModels = new ArrayList<>();
        if(televisionShowCreditDomainModels != null && televisionShowCreditDomainModels.size()>0) {
            for (TelevisionShowCreditDomainModel televisionShowCreditDomainModel : televisionShowCreditDomainModels) {
                televisionShowCreditPresentationModels.add(mapToPresentationModel(televisionShowCreditDomainModel));
            }
        }
        return televisionShowCreditPresentationModels;
    }
}
