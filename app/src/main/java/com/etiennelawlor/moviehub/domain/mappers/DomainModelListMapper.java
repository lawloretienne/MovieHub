package com.etiennelawlor.moviehub.domain.mappers;

import java.util.List;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface DomainModelListMapper<DataModel, DomainModel> {
    List<DomainModel> mapListToDomainModelList(List<DataModel> dataModels);
}