package com.etiennelawlor.moviehub.data.local.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class RealmImages extends RealmObject {

    // region Fields
    public String baseUrl;
    public String secureBaseUrl;
    public RealmList<RealmString> backdropSizes = null;
    public RealmList<RealmString> logoSizes = null;
    public RealmList<RealmString> posterSizes = null;
    public RealmList<RealmString> profileSizes = null;
    public RealmList<RealmString> stillSizes = null;
    // endregion

    // region Getters

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public RealmList<RealmString> getBackdropSizes() {
        return backdropSizes;
    }

    public RealmList<RealmString> getLogoSizes() {
        return logoSizes;
    }

    public RealmList<RealmString> getPosterSizes() {
        return posterSizes;
    }

    public RealmList<RealmString> getProfileSizes() {
        return profileSizes;
    }

    public RealmList<RealmString> getStillSizes() {
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

    public void setBackdropSizes(RealmList<RealmString> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public void setLogoSizes(RealmList<RealmString> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public void setPosterSizes(RealmList<RealmString> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public void setProfileSizes(RealmList<RealmString> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public void setStillSizes(RealmList<RealmString> stillSizes) {
        this.stillSizes = stillSizes;
    }

    // endregion
}
