package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieReleaseDate {

    // region Fields
    @SerializedName("certification")
    public String certification;
    @SerializedName("iso_639_1")
    public String iso6391;
    @SerializedName("note")
    public String note;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("type")
    public int type;
    // endregion

    // region Getters

    public String getCertification() {
        return certification;
    }

    public String getIso6391() {
        return iso6391;
    }

    public String getNote() {
        return note;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getType() {
        return type;
    }

    // endregion

    // region Setters

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setType(int type) {
        this.type = type;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieReleaseDate{" +
                "certification='" + certification + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                ", note='" + note + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", type=" + type +
                '}';
    }
}
