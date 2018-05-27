package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.NetworkRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.NetworkDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class NetworkRealmModelMapper implements RealmModelMapper<NetworkDataModel, NetworkRealmModel>, RealmModelListMapper<NetworkDataModel, NetworkRealmModel> {

    @Override
    public NetworkRealmModel mapToRealmModel(NetworkDataModel networkDataModel) {
        NetworkRealmModel realmNetwork = Realm.getDefaultInstance().createObject(NetworkRealmModel.class);

        realmNetwork.setId(networkDataModel.getId());
        realmNetwork.setName(networkDataModel.getName());

        return realmNetwork;
    }

    @Override
    public NetworkDataModel mapFromRealmModel(NetworkRealmModel networkRealmModel) {
        NetworkDataModel networkDataModel = new NetworkDataModel();
        networkDataModel.setId(networkRealmModel.getId());
        networkDataModel.setName(networkRealmModel.getName());

        return networkDataModel;
    }

    @Override
    public RealmList<NetworkRealmModel> mapListToRealmModelList(List<NetworkDataModel> networkDataModels) {
        RealmList<NetworkRealmModel> networkRealmModels = new RealmList<>();
        if(networkDataModels != null && networkDataModels.size()>0) {
            for (NetworkDataModel networkDataModel : networkDataModels) {
                networkRealmModels.add(mapToRealmModel(networkDataModel));
            }
        }
        return networkRealmModels;
    }

    @Override
    public List<NetworkDataModel> mapListFromRealmModelList(RealmList<NetworkRealmModel> networkRealmModels) {
        List<NetworkDataModel> networkDataModels = new ArrayList<>();
        for(NetworkRealmModel networkRealmModel : networkRealmModels){
            networkDataModels.add(mapFromRealmModel(networkRealmModel));
        }
        return networkDataModels;
    }
}
