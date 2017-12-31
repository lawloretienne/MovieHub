package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class ImagesRealmModel extends RealmObject {

    // region Fields
    public String baseUrl;
    public String secureBaseUrl;
    public RealmList<StringRealmModel> backdropSizes = null;
    public RealmList<StringRealmModel> logoSizes = null;
    public RealmList<StringRealmModel> posterSizes = null;
    public RealmList<StringRealmModel> profileSizes = null;
    public RealmList<StringRealmModel> stillSizes = null;
    // endregion

    // region Getters

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public RealmList<StringRealmModel> getBackdropSizes() {
        return backdropSizes;
    }

    public RealmList<StringRealmModel> getLogoSizes() {
        return logoSizes;
    }

    public RealmList<StringRealmModel> getPosterSizes() {
        return posterSizes;
    }

    public RealmList<StringRealmModel> getProfileSizes() {
        return profileSizes;
    }

    public RealmList<StringRealmModel> getStillSizes() {
        return stillSizes;
    }

    // endregion

    // region Setters

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }

    public void setBackdropSizes(RealmList<StringRealmModel> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public void setLogoSizes(RealmList<StringRealmModel> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public void setPosterSizes(RealmList<StringRealmModel> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public void setProfileSizes(RealmList<StringRealmModel> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public void setStillSizes(RealmList<StringRealmModel> stillSizes) {
        this.stillSizes = stillSizes;
    }

    // endregion
}
