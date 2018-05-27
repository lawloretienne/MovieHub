package com.etiennelawlor.moviehub.data.repositories.models;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class ReleaseDateDataModel {

    // region Fields
    public String certification;
    public String iso6391;
    public String note;
    public String releaseDate;
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
        return "ReleaseDateDataModel{" +
                "certification='" + certification + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                ", note='" + note + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", type=" + type +
                '}';
    }
}
