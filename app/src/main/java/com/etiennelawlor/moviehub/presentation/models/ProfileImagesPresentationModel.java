package com.etiennelawlor.moviehub.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesPresentationModel implements Parcelable {

    // region Fields
    @SerializedName("profiles")
    public List<ProfileImagePresentationModel> profiles = null;
    // endregion

    // region Constructors
    public ProfileImagesPresentationModel() {
    }

    protected ProfileImagesPresentationModel(Parcel in) {
        this.profiles = new ArrayList<ProfileImagePresentationModel>();
        in.readList(this.profiles, ProfileImagePresentationModel.class.getClassLoader());
    }
    // endregion

    // region Getters

    public List<ProfileImagePresentationModel> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(List<ProfileImagePresentationModel> profiles) {
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

    public static final Creator<ProfileImagesPresentationModel> CREATOR = new Creator<ProfileImagesPresentationModel>() {
        @Override
        public ProfileImagesPresentationModel createFromParcel(Parcel source) {
            return new ProfileImagesPresentationModel(source);
        }

        @Override
        public ProfileImagesPresentationModel[] newArray(int size) {
            return new ProfileImagesPresentationModel[size];
        }
    };

    @Override
    public String toString() {
        return "ProfileImagesPresentationModel{" +
                "profiles=" + profiles +
                '}';
    }
}
