package com.etiennelawlor.moviehub.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesDomainModel implements Parcelable {

    // region Fields
    @SerializedName("profiles")
    public List<ProfileImageDomainModel> profiles = null;
    // endregion

    // region Constructors
    public ProfileImagesDomainModel() {
    }

    protected ProfileImagesDomainModel(Parcel in) {
        this.profiles = new ArrayList<ProfileImageDomainModel>();
        in.readList(this.profiles, ProfileImageDomainModel.class.getClassLoader());
    }
    // endregion

    // region Getters

    public List<ProfileImageDomainModel> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(List<ProfileImageDomainModel> profiles) {
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

    public static final Creator<ProfileImagesDomainModel> CREATOR = new Creator<ProfileImagesDomainModel>() {
        @Override
        public ProfileImagesDomainModel createFromParcel(Parcel source) {
            return new ProfileImagesDomainModel(source);
        }

        @Override
        public ProfileImagesDomainModel[] newArray(int size) {
            return new ProfileImagesDomainModel[size];
        }
    };

    @Override
    public String toString() {
        return "ProfileImagesDomainModel{" +
                "profiles=" + profiles +
                '}';
    }
}
