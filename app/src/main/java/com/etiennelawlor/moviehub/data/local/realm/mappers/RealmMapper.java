package com.etiennelawlor.moviehub.data.local.realm.mappers;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface RealmMapper<Pojo, RealmObject> {
    RealmObject mapToRealmObject(Pojo data);
    Pojo mapFromRealmObject(RealmObject data);
}
