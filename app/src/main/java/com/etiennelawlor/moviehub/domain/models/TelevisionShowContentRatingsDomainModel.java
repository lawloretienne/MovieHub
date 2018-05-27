package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowContentRatingsDomainModel {

    // region Fields
    public int id;
    public List<ContentRatingDomainModel> contentRatings = null;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<ContentRatingDomainModel> getContentRatings() {
        return contentRatings;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setContentRatings(List<ContentRatingDomainModel> contentRatings) {
        this.contentRatings = contentRatings;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowContentRatingsDomainModel{" +
                "id=" + id +
                ", contentRatings=" + contentRatings +
                '}';
    }
}
