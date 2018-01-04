package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesDomainModel {

    // region Fields
    public List<ProfileImageDomainModel> profiles = null;
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

    @Override
    public String toString() {
        return "ProfileImagesDomainModel{" +
                "profiles=" + profiles +
                '}';
    }
}
