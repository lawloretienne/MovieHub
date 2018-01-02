package com.etiennelawlor.moviehub.data.repositories.models;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenreDataModel {

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

    @Override
    public String toString() {
        return "GenreDataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
