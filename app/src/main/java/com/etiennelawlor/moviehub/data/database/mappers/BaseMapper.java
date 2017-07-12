package com.etiennelawlor.moviehub.data.database.mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public abstract class BaseMapper<Pojo, R> {

    public abstract Pojo mapFromRealmObject(R realmObject);

    public abstract R mapToRealmObject(Pojo pojo);

    public List<Pojo> mapFromRealmList(List<R> realmObjectList) {
        List<Pojo> pojoList = null;
        if (realmObjectList != null) {
            pojoList = new ArrayList<>();
            Pojo pojo;
            for (R realmObject : realmObjectList) {
                pojo = mapFromRealmObject(realmObject);
                pojoList.add(pojo);
            }
        }
        return pojoList;
    }

//    public RealmList<R extends RealmObject> mapToRealmList(List<Pojo> pojoList) {
//        RealmList<R> realmObjectList = null;
//        if (pojoList != null) {
//            realmObjectList = new RealmList();
//            R realmObject;
//            for (Pojo pojo : pojoList) {
//                realmObject = mapToRealmObject(pojo);
//                realmObjectList.add(realmObject);
//            }
//        }
//        return realmObjectList;
//    }
}