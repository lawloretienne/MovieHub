package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.NetworkDomainModel;
import com.etiennelawlor.moviehub.presentation.models.NetworkPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkPresentationModelMapper implements PresentationModelMapper<NetworkDomainModel, NetworkPresentationModel>, PresentationModelListMapper<NetworkDomainModel, NetworkPresentationModel> {

    @Override
    public NetworkPresentationModel mapToPresentationModel(NetworkDomainModel networkDomainModel) {
        NetworkPresentationModel networkPresentationModel = new NetworkPresentationModel();
        networkPresentationModel.setId(networkDomainModel.getId());
        networkPresentationModel.setName(networkDomainModel.getName());
        return networkPresentationModel;
    }

    @Override
    public List<NetworkPresentationModel> mapListToPresentationModelList(List<NetworkDomainModel> networkDomainModels) {
        List<NetworkPresentationModel> networkPresentationModels = new ArrayList<>();
        if(networkDomainModels != null && networkDomainModels.size()>0) {
            for (NetworkDomainModel networkDomainModel : networkDomainModels) {
                networkPresentationModels.add(mapToPresentationModel(networkDomainModel));
            }
        }
        return networkPresentationModels;
    }

}
