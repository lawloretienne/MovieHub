package com.etiennelawlor.moviehub.data.local.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class RealmConfiguration extends RealmObject {

    // region Fields
    public RealmImages images;
    public RealmList<RealmString> changeKeys = null;
    // endregion

    // region Getters
    public RealmImages getImages() {
        return images;
    }

    public RealmList<RealmString> getChangeKeys() {
        return changeKeys;
    }
    // endregion

    // region Setters

    public void setImages(RealmImages images) {
        this.images = images;
    }

    public void setChangeKeys(RealmList<RealmString> changeKeys) {
        this.changeKeys = changeKeys;
    }
    // endregion
}
