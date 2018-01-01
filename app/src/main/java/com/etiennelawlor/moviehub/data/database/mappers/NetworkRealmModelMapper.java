package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.NetworkRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.NetworkDataModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class NetworkRealmModelMapper implements RealmModelMapper<NetworkDataModel, NetworkRealmModel> {

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
}
