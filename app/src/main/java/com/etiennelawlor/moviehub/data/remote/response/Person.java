package com.etiennelawlor.moviehub.data.remote.response;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.Palette;

import com.etiennelawlor.moviehub.util.ConfigurationUtility;
import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class Person implements Parcelable {

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
    public ProfileImages images;

    private Palette profilePalette;
    // endregion

    // region Constructors
    public Person() {
    }

    protected Person(Parcel in) {
        this.biography = in.readString();
        this.birthday = in.readString();
        this.deathday = in.readString();
        this.id = in.readInt();
        this.imdbId = in.readString();
        this.name = in.readString();
        this.placeOfBirth = in.readString();
        this.profilePath = in.readString();
        this.images = in.readParcelable(ProfileImages.class.getClassLoader());
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

    public ProfileImages getImages() {
        return images;
    }

    public Palette getProfilePalette() {
        return profilePalette;
    }

    public String getProfileUrl(Context context){
        String secureBaseUrl = ConfigurationUtility.getSecureBaseUrl(context);
        String profileSize = ConfigurationUtility.getProfileSize(context);
        String profileUrl = String.format("%s%s%s", secureBaseUrl, profileSize, profilePath);

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

    public void setImages(ProfileImages images) {
        this.images = images;
    }

    public void setProfilePalette(Palette profilePalette) {
        this.profilePalette = profilePalette;
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

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
