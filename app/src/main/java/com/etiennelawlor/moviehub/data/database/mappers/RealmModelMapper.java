package com.etiennelawlor.moviehub.data.database.mappers;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface RealmModelMapper<Pojo, RealmModel> {
    RealmModel mapToRealmModel(Pojo pojo);
    Pojo mapFromRealmModel(RealmModel realmModel);
}
