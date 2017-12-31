package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.NetworkRealmModel;
import com.etiennelawlor.moviehub.data.network.response.Network;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class NetworkRealmModelMapper implements RealmModelMapper<Network, NetworkRealmModel> {

    @Override
    public NetworkRealmModel mapToRealmModel(Network network) {
        NetworkRealmModel realmNetwork = Realm.getDefaultInstance().createObject(NetworkRealmModel.class);

        realmNetwork.setId(network.getId());
        realmNetwork.setName(network.getName());

        return realmNetwork;
    }

    @Override
    public Network mapFromRealmModel(NetworkRealmModel networkRealmModel) {
        Network network = new Network();
        network.setId(networkRealmModel.getId());
        network.setName(networkRealmModel.getName());

        return network;
    }
}
