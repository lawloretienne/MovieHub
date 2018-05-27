package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileImagesRealmModel extends RealmObject {

    // region Fields
    public RealmList<ProfileImageRealmModel> profiles = null;
    // endregion

    // region Getters

    public RealmList<ProfileImageRealmModel> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(RealmList<ProfileImageRealmModel> profiles) {
        this.profiles = profiles;
    }

    // endregion
}
