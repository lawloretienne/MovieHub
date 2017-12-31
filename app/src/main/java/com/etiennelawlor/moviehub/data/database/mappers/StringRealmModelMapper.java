package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.StringRealmModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class StringRealmModelMapper implements RealmModelMapper<String, StringRealmModel> {

    @Override
    public StringRealmModel mapToRealmModel(String s) {
        StringRealmModel realmString = Realm.getDefaultInstance().createObject(StringRealmModel.class);

        realmString.setValue(s);

        return realmString;
    }

    @Override
    public String mapFromRealmModel(StringRealmModel stringRealmModel) {
        String string = stringRealmModel.getValue();

        return string;
    }
}
