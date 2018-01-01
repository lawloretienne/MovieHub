package com.etiennelawlor.moviehub.data.repositories.mappers;

import java.util.List;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface DataModelListMapper<NetworkResponseModel, DataModel> {
    List<DataModel> mapListToDataModelList(List<NetworkResponseModel> networkResponseModels);
}