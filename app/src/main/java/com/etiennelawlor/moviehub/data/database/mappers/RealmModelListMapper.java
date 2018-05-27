package com.etiennelawlor.moviehub.data.database.mappers;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by etiennelawlor on 1/3/18.
 */

public interface RealmModelListMapper<Pojo, RealmModel> {
    RealmList<RealmModel> mapListToRealmModelList(List<Pojo> pojos);
    List<Pojo> mapListFromRealmModelList(RealmList<RealmModel> realmModels);
}
