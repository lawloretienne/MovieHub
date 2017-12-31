package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class ConfigurationRealmModel extends RealmObject {

    // region Fields
    public ImagesRealmModel images;
    public RealmList<StringRealmModel> changeKeys = null;
    // endregion

    // region Getters
    public ImagesRealmModel getImages() {
        return images;
    }

    public RealmList<StringRealmModel> getChangeKeys() {
        return changeKeys;
    }
    // endregion

    // region Setters

    public void setImages(ImagesRealmModel images) {
        this.images = images;
    }

    public void setChangeKeys(RealmList<StringRealmModel> changeKeys) {
        this.changeKeys = changeKeys;
    }
    // endregion
}
