package com.etiennelawlor.moviehub.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDateDomainModel {

    // region Fields
    @SerializedName("iso_3166_1")
    public String iso31661;
    @SerializedName("release_dates")
    public List<ReleaseDateDomainModel> releaseDates = null;
    // endregion

    // region Getters

    public String getIso31661() {
        return iso31661;
    }

    public List<ReleaseDateDomainModel> getReleaseDates() {
        return releaseDates;
    }

    // endregion

    // region Setters

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public void setReleaseDates(List<ReleaseDateDomainModel> releaseDates) {
        this.releaseDates = releaseDates;
    }

    // endregion


    @Override
    public String toString() {
        return "MovieReleaseDateDomainModel{" +
                "iso31661='" + iso31661 + '\'' +
                ", releaseDates=" + releaseDates +
                '}';
    }
}
