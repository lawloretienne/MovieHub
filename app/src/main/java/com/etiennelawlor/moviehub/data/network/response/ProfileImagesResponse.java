package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileImagesResponse {

    // region Fields
    @SerializedName("profiles")
    public List<ProfileImageResponse> profiles = null;
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

    @Override
    public String toString() {
        return "ProfileImagesResponse{" +
                "profiles=" + profiles +
                '}';
    }
}
