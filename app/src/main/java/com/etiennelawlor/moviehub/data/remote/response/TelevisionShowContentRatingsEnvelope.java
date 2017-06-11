package com.etiennelawlor.moviehub.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowContentRatingsEnvelope {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<ContentRating> contentRatings = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<ContentRating> getContentRatings() {
        return contentRatings;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setContentRatings(List<ContentRating> contentRatings) {
        this.contentRatings = contentRatings;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowContentRatingsEnvelope{" +
                "id=" + id +
                ", contentRatings=" + contentRatings +
                '}';
    }
}
