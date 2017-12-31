package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.IntegerRealmModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class IntegerRealmModelMapper implements RealmModelMapper<Integer, IntegerRealmModel> {

    @Override
    public IntegerRealmModel mapToRealmModel(Integer integer) {
        IntegerRealmModel realmInteger = Realm.getDefaultInstance().createObject(IntegerRealmModel.class);

        realmInteger.setValue(integer);

        return realmInteger;
    }

    @Override
    public Integer mapFromRealmModel(IntegerRealmModel integerRealmModel) {
        Integer integer = integerRealmModel.getValue();

        return integer;
    }
}
