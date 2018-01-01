package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditsDataModelMapper implements DataModelMapper<TelevisionShowCreditsResponse, TelevisionShowCreditsDataModel> {

    // region Member Variables
    private TelevisionShowCreditDataModelMapper televisionShowCreditDataModelMapper = new TelevisionShowCreditDataModelMapper();
    // endregion

    @Override
    public TelevisionShowCreditsDataModel mapToDataModel(TelevisionShowCreditsResponse televisionShowCreditsResponse) {
        TelevisionShowCreditsDataModel televisionShowCreditsDataModel = new TelevisionShowCreditsDataModel();
        televisionShowCreditsDataModel.setId(televisionShowCreditsResponse.getId());

        List<TelevisionShowCreditResponse> castTelevisionShowCreditResponses = televisionShowCreditsResponse.getCast();
        List<TelevisionShowCreditDataModel> castTelevisionShowCreditsDataModels = new ArrayList<>();
        if(castTelevisionShowCreditResponses != null && castTelevisionShowCreditResponses.size()>0) {
            for (TelevisionShowCreditResponse televisionShowCreditResponse : castTelevisionShowCreditResponses) {
                castTelevisionShowCreditsDataModels.add(televisionShowCreditDataModelMapper.mapToDataModel(televisionShowCreditResponse));
            }
        }
        televisionShowCreditsDataModel.setCast(castTelevisionShowCreditsDataModels);

        List<TelevisionShowCreditResponse> crewTelevisionShowCreditResponses = televisionShowCreditsResponse.getCrew();
        List<TelevisionShowCreditDataModel> crewTelevisionShowCreditsDataModels = new ArrayList<>();
        if(crewTelevisionShowCreditResponses != null && crewTelevisionShowCreditResponses.size()>0) {
            for (TelevisionShowCreditResponse televisionShowCreditResponse : crewTelevisionShowCreditResponses) {
                crewTelevisionShowCreditsDataModels.add(televisionShowCreditDataModelMapper.mapToDataModel(televisionShowCreditResponse));
            }
        }
        televisionShowCreditsDataModel.setCrew(crewTelevisionShowCreditsDataModels);

        return televisionShowCreditsDataModel;
    }
}
