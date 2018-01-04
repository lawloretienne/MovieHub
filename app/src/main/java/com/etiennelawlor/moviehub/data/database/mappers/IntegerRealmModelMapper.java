package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.IntegerRealmModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class IntegerRealmModelMapper implements RealmModelMapper<Integer, IntegerRealmModel>, RealmModelListMapper<Integer, IntegerRealmModel> {

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

    @Override
    public RealmList<IntegerRealmModel> mapListToRealmModelList(List<Integer> integers) {
        RealmList<IntegerRealmModel> realmIntegers = new RealmList<>();
        if(integers != null && integers.size()>0) {
            for (Integer integer : integers) {
                realmIntegers.add(mapToRealmModel(integer));
            }
        }
        return realmIntegers;
    }

    @Override
    public List<Integer> mapListFromRealmModelList(RealmList<IntegerRealmModel> integerRealmModels) {
        List<Integer> integers = new ArrayList<>();
        for(IntegerRealmModel integerRealmModel : integerRealmModels){
            integers.add(mapFromRealmModel(integerRealmModel));
        }
        return integers;
    }
}
