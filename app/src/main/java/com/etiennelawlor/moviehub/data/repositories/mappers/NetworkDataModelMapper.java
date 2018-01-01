package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.NetworkResponse;
import com.etiennelawlor.moviehub.data.repositories.models.NetworkDataModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkDataModelMapper implements DataModelMapper<NetworkResponse, NetworkDataModel> {
    @Override
    public NetworkDataModel mapToDataModel(NetworkResponse networkResponse) {
        NetworkDataModel networkDataModel = new NetworkDataModel();
        networkDataModel.setId(networkResponse.getId());
        networkDataModel.setName(networkResponse.getName());
        return networkDataModel;
    }
}
