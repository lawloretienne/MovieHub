package com.etiennelawlor.moviehub.data.repositories.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowContentRatingsDataModel {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<ContentRatingDataModel> contentRatings = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<ContentRatingDataModel> getContentRatings() {
        return contentRatings;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setContentRatings(List<ContentRatingDataModel> contentRatings) {
        this.contentRatings = contentRatings;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowContentRatingsResponse{" +
                "id=" + id +
                ", contentRatings=" + contentRatings +
                '}';
    }
}
