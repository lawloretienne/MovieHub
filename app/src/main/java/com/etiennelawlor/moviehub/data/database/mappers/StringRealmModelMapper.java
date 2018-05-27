package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.StringRealmModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class StringRealmModelMapper implements RealmModelMapper<String, StringRealmModel>, RealmModelListMapper<String, StringRealmModel> {

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

    @Override
    public RealmList<StringRealmModel> mapListToRealmModelList(List<String> strings) {
        RealmList<StringRealmModel> stringRealmModels = new RealmList<>();
        if(strings != null && strings.size()>0) {
            for (String string : strings) {
                stringRealmModels.add(mapToRealmModel(string));
            }
        }
        return stringRealmModels;
    }

    @Override
    public List<String> mapListFromRealmModelList(RealmList<StringRealmModel> stringRealmModels) {
        List<String> strings = new ArrayList<>();
        for(StringRealmModel stringRealmModel : stringRealmModels){
            strings.add(mapFromRealmModel(stringRealmModel));
        }
        return strings;
    }
}
