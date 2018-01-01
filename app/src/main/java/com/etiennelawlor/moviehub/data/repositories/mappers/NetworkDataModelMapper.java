package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.NetworkResponse;
import com.etiennelawlor.moviehub.data.repositories.models.NetworkDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkDataModelMapper implements DataModelMapper<NetworkResponse, NetworkDataModel>, DataModelListMapper<NetworkResponse, NetworkDataModel> {
    @Override
    public NetworkDataModel mapToDataModel(NetworkResponse networkResponse) {
        NetworkDataModel networkDataModel = new NetworkDataModel();
        networkDataModel.setId(networkResponse.getId());
        networkDataModel.setName(networkResponse.getName());
        return networkDataModel;
    }

    @Override
    public List<NetworkDataModel> mapListToDataModelList(List<NetworkResponse> networkResponses) {
        List<NetworkDataModel> networkDataModels = new ArrayList<>();
        if(networkResponses != null && networkResponses.size()>0) {
            for (NetworkResponse networkResponse : networkResponses) {
                networkDataModels.add(mapToDataModel(networkResponse));
            }
        }
        return networkDataModels;
    }
}
