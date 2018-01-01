package com.etiennelawlor.moviehub.domain.mappers;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface DomainModelMapper<DataModel, DomainModel> {
    DomainModel mapToDomainModel(DataModel dataModel);
}