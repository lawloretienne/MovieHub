package com.etiennelawlor.moviehub.data.repositories.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesDataModel implements Parcelable {

    // region Fields
    @SerializedName("profiles")
    public List<ProfileImageDataModel> profiles = null;
    // endregion

    // region Constructors
    public ProfileImagesDataModel() {
    }

    protected ProfileImagesDataModel(Parcel in) {
        this.profiles = new ArrayList<ProfileImageDataModel>();
        in.readList(this.profiles, ProfileImageDataModel.class.getClassLoader());
    }
    // endregion

    // region Getters

    public List<ProfileImageDataModel> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(List<ProfileImageDataModel> profiles) {
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

    public static final Parcelable.Creator<ProfileImagesDataModel> CREATOR = new Parcelable.Creator<ProfileImagesDataModel>() {
        @Override
        public ProfileImagesDataModel createFromParcel(Parcel source) {
            return new ProfileImagesDataModel(source);
        }

        @Override
        public ProfileImagesDataModel[] newArray(int size) {
            return new ProfileImagesDataModel[size];
        }
    };

    @Override
    public String toString() {
        return "ProfileImagesDataModel{" +
                "profiles=" + profiles +
                '}';
    }
}
