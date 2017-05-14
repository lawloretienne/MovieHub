package com.etiennelawlor.moviehub.data.local.realm.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class RealmGenre extends RealmObject {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    // endregion

    // region Constructors
    public RealmGenre() {
    }
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
