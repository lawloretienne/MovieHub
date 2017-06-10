package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmString;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class StringRealmMapper implements RealmMapper<String, RealmString> {

    @Override
    public RealmString mapToRealmObject(String string) {
        RealmString realmString = Realm.getDefaultInstance().createObject(RealmString.class);

        realmString.setValue(string);

        return realmString;
    }

    @Override
    public String mapFromRealmObject(RealmString realmString) {
        String string = realmString.getValue();

        return string;
    }
}
