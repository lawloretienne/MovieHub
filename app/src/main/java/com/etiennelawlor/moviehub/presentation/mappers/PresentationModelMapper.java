package com.etiennelawlor.moviehub.presentation.mappers;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface PresentationModelMapper<DomainModel, PresentationModel> {
    PresentationModel mapToPresentationModel(DomainModel domainModel);
}