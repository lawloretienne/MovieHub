package com.etiennelawlor.moviehub.data.repositories.models;

import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDateDataModel {

    // region Fields
    public String iso31661;
    public List<ReleaseDateDataModel> releaseDates = null;
    // endregion

    // region Getters

    public String getIso31661() {
        return iso31661;
    }

    public List<ReleaseDateDataModel> getReleaseDates() {
        return releaseDates;
    }

    // endregion

    // region Setters

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public void setReleaseDates(List<ReleaseDateDataModel> releaseDates) {
        this.releaseDates = releaseDates;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDateDataModel{" +
                "iso31661='" + iso31661 + '\'' +
                ", releaseDates=" + releaseDates +
                '}';
    }
}
