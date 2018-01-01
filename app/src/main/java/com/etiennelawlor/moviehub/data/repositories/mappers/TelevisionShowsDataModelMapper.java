package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

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

    @Override
    public TelevisionShowsDataModel mapToDataModel(TelevisionShowsResponse televisionShowsResponse) {
        TelevisionShowsDataModel televisionShowsDataModel = new TelevisionShowsDataModel();

        List<TelevisionShowResponse> televisionShows = televisionShowsResponse.getTelevisionShows();
        televisionShowsDataModel.setLastPage(televisionShows.size() < PAGE_SIZE ? true : false);
        televisionShowsDataModel.setPageNumber(televisionShowsResponse.getPage());
        televisionShowsDataModel.setTelevisionShows(televisionShows);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        televisionShowsDataModel.setExpiredAt(calendar.getTime());

        return televisionShowsDataModel;
    }
}
