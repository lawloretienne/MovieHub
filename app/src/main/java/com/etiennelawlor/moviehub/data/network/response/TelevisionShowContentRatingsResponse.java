package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowContentRatingsResponse {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public List<ContentRatingResponse> contentRatings = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<ContentRatingResponse> getContentRatings() {
        return contentRatings;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setContentRatings(List<ContentRatingResponse> contentRatings) {
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
