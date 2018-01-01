package com.etiennelawlor.moviehub.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileImagesResponse implements Parcelable {

    // region Fields
    @SerializedName("profiles")
    public List<ProfileImageResponse> profiles = null;
    // endregion

    // region Constructors
    public ProfileImagesResponse() {
    }

    protected ProfileImagesResponse(Parcel in) {
        this.profiles = new ArrayList<ProfileImageResponse>();
        in.readList(this.profiles, ProfileImageResponse.class.getClassLoader());
    }
    // endregion

    // region Getters

    public List<ProfileImageResponse> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(List<ProfileImageResponse> profiles) {
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

    public static final Parcelable.Creator<ProfileImagesResponse> CREATOR = new Parcelable.Creator<ProfileImagesResponse>() {
        @Override
        public ProfileImagesResponse createFromParcel(Parcel source) {
            return new ProfileImagesResponse(source);
        }

        @Override
        public ProfileImagesResponse[] newArray(int size) {
            return new ProfileImagesResponse[size];
        }
    };

    @Override
    public String toString() {
        return "ProfileImagesResponse{" +
                "profiles=" + profiles +
                '}';
    }
}
