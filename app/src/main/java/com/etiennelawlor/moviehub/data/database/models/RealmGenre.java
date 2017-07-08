package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class RealmGenre extends RealmObject {

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
