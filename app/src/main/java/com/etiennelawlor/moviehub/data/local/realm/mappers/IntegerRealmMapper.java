package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmInteger;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class IntegerRealmMapper implements RealmMapper<Integer, RealmInteger> {

    @Override
    public RealmInteger mapToRealmObject(Integer integer) {
        RealmInteger realmInteger = Realm.getDefaultInstance().createObject(RealmInteger.class);

        realmInteger.setValue(integer);

        return realmInteger;
    }

    @Override
    public Integer mapFromRealmObject(RealmInteger realmInteger) {
        Integer integer = realmInteger.getValue();

        return integer;
    }
}
