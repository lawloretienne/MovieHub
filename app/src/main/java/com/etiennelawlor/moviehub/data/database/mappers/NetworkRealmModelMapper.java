package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.NetworkRealmModel;
import com.etiennelawlor.moviehub.data.network.response.NetworkResponse;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class NetworkRealmModelMapper implements RealmModelMapper<NetworkResponse, NetworkRealmModel> {

    @Override
    public NetworkRealmModel mapToRealmModel(NetworkResponse network) {
        NetworkRealmModel realmNetwork = Realm.getDefaultInstance().createObject(NetworkRealmModel.class);

        realmNetwork.setId(network.getId());
        realmNetwork.setName(network.getName());

        return realmNetwork;
    }

    @Override
    public NetworkResponse mapFromRealmModel(NetworkRealmModel networkRealmModel) {
        NetworkResponse network = new NetworkResponse();
        network.setId(networkRealmModel.getId());
        network.setName(networkRealmModel.getName());

        return network;
    }
}
