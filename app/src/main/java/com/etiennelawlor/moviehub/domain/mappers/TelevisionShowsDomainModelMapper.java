package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class TelevisionShowsDomainModelMapper implements DomainModelMapper<TelevisionShowsDataModel, TelevisionShowsDomainModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private TelevisionShowDomainModelMapper televisionShowDomainModelMapper = new TelevisionShowDomainModelMapper();
    // endregion

    @Override
    public TelevisionShowsDomainModel mapToDomainModel(TelevisionShowsDataModel televisionShowsDataModel) {
        TelevisionShowsDomainModel televisionShowsDomainModel = new TelevisionShowsDomainModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        televisionShowsDomainModel.setExpiredAt(calendar.getTime());
        televisionShowsDomainModel.setLastPage(televisionShowsDataModel.getTelevisionShows().size() < PAGE_SIZE);
        televisionShowsDomainModel.setTelevisionShows(televisionShowDomainModelMapper.mapListToDomainModelList(televisionShowsDataModel.getTelevisionShows()));
        televisionShowsDomainModel.setPageNumber(televisionShowsDataModel.getPageNumber());
        return televisionShowsDomainModel;
    }
}
