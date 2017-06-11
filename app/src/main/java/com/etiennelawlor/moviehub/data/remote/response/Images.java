package com.etiennelawlor.moviehub.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/17/16.
 */

public class Images {

    // region Fields
    @SerializedName("base_url")
    public String baseUrl;
    @SerializedName("secure_base_url")
    public String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    public List<String> backdropSizes = null;
    @SerializedName("logo_sizes")
    public List<String> logoSizes = null;
    @SerializedName("poster_sizes")
    public List<String> posterSizes = null;
    @SerializedName("profile_sizes")
    public List<String> profileSizes = null;
    @SerializedName("still_sizes")
    public List<String> stillSizes = null;
    // endregion

    // region Getters

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public List<String> getLogoSizes() {
        return logoSizes;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public List<String> getProfileSizes() {
        return profileSizes;
    }

    public List<String> getStillSizes() {
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

    public void setBackdropSizes(List<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public void setLogoSizes(List<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public void setPosterSizes(List<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public void setProfileSizes(List<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public void setStillSizes(List<String> stillSizes) {
        this.stillSizes = stillSizes;
    }

    // endregion

    @Override
    public String toString() {
        return "Images{" +
                "baseUrl='" + baseUrl + '\'' +
                ", secureBaseUrl='" + secureBaseUrl + '\'' +
                ", backdropSizes=" + backdropSizes +
                ", logoSizes=" + logoSizes +
                ", posterSizes=" + posterSizes +
                ", profileSizes=" + profileSizes +
                ", stillSizes=" + stillSizes +
                '}';
    }
}
