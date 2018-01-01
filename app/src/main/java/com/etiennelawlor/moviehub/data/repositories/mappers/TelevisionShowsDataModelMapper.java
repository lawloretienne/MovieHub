package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class TelevisionShowsDataModelMapper implements DataModelMapper<TelevisionShowsResponse, TelevisionShowsDataModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private TelevisionShowDataModelMapper televisionShowDataModelMapper = new TelevisionShowDataModelMapper();
    // endregion

    @Override
    public TelevisionShowsDataModel mapToDataModel(TelevisionShowsResponse televisionShowsResponse) {
        TelevisionShowsDataModel televisionShowsDataModel = new TelevisionShowsDataModel();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        televisionShowsDataModel.setExpiredAt(calendar.getTime());
        televisionShowsDataModel.setLastPage(televisionShowsResponse.getTelevisionShows().size() < PAGE_SIZE ? true : false);
        List<TelevisionShowResponse> televisionShowResponses = televisionShowsResponse.getTelevisionShows();
        List<TelevisionShowDataModel> televisionShowDataModels = new ArrayList<>();
        if(televisionShowResponses != null && televisionShowResponses.size()>0) {
            for (TelevisionShowResponse televisionShowResponse : televisionShowResponses) {
                televisionShowDataModels.add(televisionShowDataModelMapper.mapToDataModel(televisionShowResponse));
            }
        }
        televisionShowsDataModel.setTelevisionShows(televisionShowDataModels);
        televisionShowsDataModel.setPageNumber(televisionShowsResponse.getPage());

        return televisionShowsDataModel;
    }
}
