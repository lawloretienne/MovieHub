package com.etiennelawlor.moviehub.data.repositories.models;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesDataModel {

    // region Fields
    public List<ProfileImageDataModel> profiles = null;
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

    @Override
    public String toString() {
        return "ProfileImagesDataModel{" +
                "profiles=" + profiles +
                '}';
    }
}
