package com.etiennelawlor.moviehub.data.local.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class RealmProfileImages extends RealmObject {

    // region Fields
    public RealmList<RealmProfileImage> profiles = null;
    // endregion

    // region Getters

    public RealmList<RealmProfileImage> getProfiles() {
        return profiles;
    }

    // endregion

    // region Setters

    public void setProfiles(RealmList<RealmProfileImage> profiles) {
        this.profiles = profiles;
    }

    // endregion
}
