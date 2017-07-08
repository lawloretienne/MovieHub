package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.RealmNetwork;
import com.etiennelawlor.moviehub.data.network.response.Network;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class NetworkRealmMapper implements RealmMapper<Network, RealmNetwork> {

    @Override
    public RealmNetwork mapToRealmObject(Network network) {
        RealmNetwork realmNetwork = Realm.getDefaultInstance().createObject(RealmNetwork.class);

        realmNetwork.setId(network.getId());
        realmNetwork.setName(network.getName());

        return realmNetwork;
    }

    @Override
    public Network mapFromRealmObject(RealmNetwork realmNetwork) {
        Network network = new Network();
        network.setId(realmNetwork.getId());
        network.setName(realmNetwork.getName());

        return network;
    }
}
