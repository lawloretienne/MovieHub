package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieReleaseDateEnvelope {

    // region Fields
    @SerializedName("iso_3166_1")
    public String iso31661;
    @SerializedName("release_dates")
    public List<MovieReleaseDate> movieReleaseDates = null;
    // endregion

    // region Getters

    public String getIso31661() {
        return iso31661;
    }

    public List<MovieReleaseDate> getMovieReleaseDates() {
        return movieReleaseDates;
    }

    // endregion

    // region Setters

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public void setMovieReleaseDates(List<MovieReleaseDate> movieReleaseDates) {
        this.movieReleaseDates = movieReleaseDates;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDateEnvelope{" +
                "iso31661='" + iso31661 + '\'' +
                ", movieReleaseDates=" + movieReleaseDates +
                '}';
    }
}
