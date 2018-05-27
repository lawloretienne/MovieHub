package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class PersonResponse {

    // region Fields
    @SerializedName("biography")
    public String biography;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("deathday")
    public String deathday;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("name")
    public String name;
    @SerializedName("place_of_birth")
    public String placeOfBirth;
    @SerializedName("profile_path")
    public String profilePath;
    @SerializedName("images")
    public ProfileImagesResponse images;
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

    public ProfileImagesResponse getImages() {
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

    public void setImages(ProfileImagesResponse images) {
        this.images = images;
    }

    // endregion

    @Override
    public String toString() {
        return "PersonResponse{" +
                "biography='" + biography + '\'' +
                ", birthday='" + birthday + '\'' +
                ", deathday='" + deathday + '\'' +
                ", id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", name='" + name + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", profilePath='" + profilePath + '\'' +
                ", images=" + images +
                '}';
    }
}
