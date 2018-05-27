package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class NetworkResponse {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("name")
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
        return "NetworkResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
