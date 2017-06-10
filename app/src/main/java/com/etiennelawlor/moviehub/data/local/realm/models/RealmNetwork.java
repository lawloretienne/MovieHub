package com.etiennelawlor.moviehub.data.local.realm.models;

import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class RealmNetwork extends RealmObject {

    // region Fields
    public int id;
    public String name;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // endregion
}
