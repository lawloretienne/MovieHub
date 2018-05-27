package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import java.util.Calendar;

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
        televisionShowsDataModel.setLastPage(televisionShowsResponse.getTelevisionShows().size() < PAGE_SIZE);
        televisionShowsDataModel.setTelevisionShows(televisionShowDataModelMapper.mapListToDataModelList(televisionShowsResponse.getTelevisionShows()));
        televisionShowsDataModel.setPageNumber(televisionShowsResponse.getPage());
        return televisionShowsDataModel;
    }
}
