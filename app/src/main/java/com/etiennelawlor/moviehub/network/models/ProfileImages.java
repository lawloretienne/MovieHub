package com.etiennelawlor.moviehub.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileImages implements Parcelable {

    // region Fields
    @SerializedName("profiles")
    public List<ProfileImage> profiles = null;
    // endregion

    // region Constructors
    public ProfileImages() {
    }

    protected ProfileImages(Parcel in) {
        this.profiles = new ArrayList<ProfileImage>();
        in.readList(this.profiles, ProfileImage.class.getClassLoader());
    }
    // endregion

    // region Getters

    public List<ProfileImage> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(List<ProfileImage> profiles) {
        this.profiles = profiles;
    }

    // endregion

    // region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.profiles);
    }
    // endregion

    public static final Parcelable.Creator<ProfileImages> CREATOR = new Parcelable.Creator<ProfileImages>() {
        @Override
        public ProfileImages createFromParcel(Parcel source) {
            return new ProfileImages(source);
        }

        @Override
        public ProfileImages[] newArray(int size) {
            return new ProfileImages[size];
        }
    };
}
