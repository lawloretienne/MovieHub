package com.etiennelawlor.moviehub.data.repositories.mappers;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface DataModelMapper<NetworkResponseModel, DataModel> {
    DataModel mapToDataModel(NetworkResponseModel networkResponseModel);
}