package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.NetworkDataModel;
import com.etiennelawlor.moviehub.domain.models.NetworkDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkDomainModelMapper implements DomainModelMapper<NetworkDataModel, NetworkDomainModel>, DomainModelListMapper<NetworkDataModel, NetworkDomainModel> {
    @Override
    public NetworkDomainModel mapToDomainModel(NetworkDataModel networkDataModel) {
        NetworkDomainModel networkDomainModel = new NetworkDomainModel();
        networkDomainModel.setId(networkDataModel.getId());
        networkDomainModel.setName(networkDataModel.getName());
        return networkDomainModel;
    }

    @Override
    public List<NetworkDomainModel> mapListToDomainModelList(List<NetworkDataModel> networkDataModels) {
        List<NetworkDomainModel> networkDomainModels = new ArrayList<>();
        if(networkDataModels != null && networkDataModels.size()>0) {
            for (NetworkDataModel networkDataModel : networkDataModels) {
                networkDomainModels.add(mapToDomainModel(networkDataModel));
            }
        }
        return networkDomainModels;
    }
}
