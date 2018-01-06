package com.etiennelawlor.moviehub.presentation.models;

import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDatePresentationModel {

    // region Fields
    public String iso31661;
    public List<ReleaseDatePresentationModel> releaseDates = null;
    // endregion

    // region Getters

    public String getIso31661() {
        return iso31661;
    }

    public List<ReleaseDatePresentationModel> getReleaseDates() {
        return releaseDates;
    }

    // endregion

    // region Setters

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public void setReleaseDates(List<ReleaseDatePresentationModel> releaseDates) {
        this.releaseDates = releaseDates;
    }

    // endregion


    @Override
    public String toString() {
        return "MovieReleaseDatePresentationModel{" +
                "iso31661='" + iso31661 + '\'' +
                ", releaseDates=" + releaseDates +
                '}';
    }
}
