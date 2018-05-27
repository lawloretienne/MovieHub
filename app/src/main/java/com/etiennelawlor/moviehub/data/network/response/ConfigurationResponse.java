package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ConfigurationResponse {

    // region Fields
    @SerializedName("images")
    public ImagesResponse images;
    @SerializedName("change_keys")
    public List<String> changeKeys = null;
    // endregion

    // region Getters

    public ImagesResponse getImages() {
        return images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    // endregion

    // region Setters

    public void setImages(ImagesResponse images) {
        this.images = images;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    // endregion


    @Override
    public String toString() {
        return "ConfigurationResponse{" +
                "images=" + images +
                ", changeKeys=" + changeKeys +
                '}';
    }
}
