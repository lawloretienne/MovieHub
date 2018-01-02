package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowsPresentationModel;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class TelevisionShowsPresentationModelMapper implements PresentationModelMapper<TelevisionShowsDomainModel, TelevisionShowsPresentationModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private TelevisionShowPresentationModelMapper televisionShowPresentationModelMapper = new TelevisionShowPresentationModelMapper();
    // endregion

    @Override
    public TelevisionShowsPresentationModel mapToPresentationModel(TelevisionShowsDomainModel televisionShowsDomainModel) {
        TelevisionShowsPresentationModel televisionShowsPresentationModel = new TelevisionShowsPresentationModel();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        televisionShowsPresentationModel.setExpiredAt(calendar.getTime());
        televisionShowsPresentationModel.setLastPage(televisionShowsDomainModel.getTelevisionShows().size() < PAGE_SIZE ? true : false);
        televisionShowsPresentationModel.setTelevisionShows(televisionShowPresentationModelMapper.mapListToPresentationModelList(televisionShowsDomainModel.getTelevisionShows()));
        televisionShowsPresentationModel.setPageNumber(televisionShowsDomainModel.getPageNumber());
        return televisionShowsPresentationModel;
    }
}
