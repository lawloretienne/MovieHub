package com.etiennelawlor.moviehub.presentation.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowContentRatingsPresentationModel {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<ContentRatingPresentationModel> contentRatings = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<ContentRatingPresentationModel> getContentRatings() {
        return contentRatings;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setContentRatings(List<ContentRatingPresentationModel> contentRatings) {
        this.contentRatings = contentRatings;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowContentRatingsPresentationModel{" +
                "id=" + id +
                ", contentRatings=" + contentRatings +
                '}';
    }
}
