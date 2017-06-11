package com.etiennelawlor.moviehub.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class Configuration {

    // region Fields
    @SerializedName("images")
    public Images images;
    @SerializedName("change_keys")
    public List<String> changeKeys = null;
    // endregion

    // region Getters

    public Images getImages() {
        return images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    // endregion

    // region Setters

    public void setImages(Images images) {
        this.images = images;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    // endregion

    @Override
    public String toString() {
        return "Configuration{" +
                "images=" + images +
                ", changeKeys=" + changeKeys +
                '}';
    }
}
