package com.etiennelawlor.moviehub.data.local.realm.models;

import com.etiennelawlor.moviehub.data.remote.response.Images;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class RealmConfiguration extends RealmObject {

    // region Fields
    @SerializedName("images")
    public RealmImages images;
    @SerializedName("change_keys")
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
