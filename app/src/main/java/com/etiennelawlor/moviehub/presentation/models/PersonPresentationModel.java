package com.etiennelawlor.moviehub.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class PersonPresentationModel implements Parcelable {

    // region Constants
    public static final String SECURE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String PROFILE_SIZE = "h632";
    // endregion

    // region Fields
    public String biography;
    public String birthday;
    public String deathday;
    public int id;
    public String imdbId;
    public String name;
    public String placeOfBirth;
    public String profilePath;
    public ProfileImagesPresentationModel images;
    // endregion

    // region Constructors
    public PersonPresentationModel() {
    }

    protected PersonPresentationModel(Parcel in) {
        this.biography = in.readString();
        this.birthday = in.readString();
        this.deathday = in.readString();
        this.id = in.readInt();
        this.imdbId = in.readString();
        this.name = in.readString();
        this.placeOfBirth = in.readString();
        this.profilePath = in.readString();
        this.images = in.readParcelable(ProfileImagesPresentationModel.class.getClassLoader());
    }
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

    public ProfileImagesPresentationModel getImages() {
        return images;
    }

    public String getProfileUrl(){
        String profileUrl = String.format("%s%s%s", SECURE_BASE_URL, PROFILE_SIZE, profilePath);
        return profileUrl;
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

    public void setImages(ProfileImagesPresentationModel images) {
        this.images = images;
    }

    // endregion

    // region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.biography);
        dest.writeString(this.birthday);
        dest.writeString(this.deathday);
        dest.writeInt(this.id);
        dest.writeString(this.imdbId);
        dest.writeString(this.name);
        dest.writeString(this.placeOfBirth);
        dest.writeString(this.profilePath);
        dest.writeParcelable(this.images, flags);
    }
    // endregion

    public static final Creator<PersonPresentationModel> CREATOR = new Creator<PersonPresentationModel>() {
        @Override
        public PersonPresentationModel createFromParcel(Parcel source) {
            return new PersonPresentationModel(source);
        }

        @Override
        public PersonPresentationModel[] newArray(int size) {
            return new PersonPresentationModel[size];
        }
    };

    @Override
    public String toString() {
        return "PersonPresentationModel{" +
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
