package com.etiennelawlor.moviehub.domain.models;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkDomainModel {

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
        return "NetworkDomainModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
