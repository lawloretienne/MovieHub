package com.etiennelawlor.moviehub.presentation.mappers;

import java.util.List;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface PresentationModelListMapper<DomainModel, PresentationModel> {
    List<PresentationModel> mapListToPresentationModelList(List<DomainModel> domainModels);
}