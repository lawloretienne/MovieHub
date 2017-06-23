package com.etiennelawlor.moviehub.data.local.realm.models;

import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class RealmPerson extends RealmObject {

    // region Fields
    public String biography;
    public String birthday;
    public String deathday;
    public int id;
    public String imdbId;
    public String name;
    public String placeOfBirth;
    public String profilePath;
    public RealmProfileImages images;
    // endregion

    // region Getters

    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getName() {
        return name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public RealmProfileImages getImages() {
        return images;
    }

    // endregion

    // region Setters

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void setImages(RealmProfileImages images) {
        this.images = images;
    }

    // endregion

}
