package com.etiennelawlor.moviehub.data.remote.response;

import android.support.v7.graphics.Palette;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MovieCredit extends Credit {

    // region Constants
    public static final String SECURE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String PROFILE_SIZE = "h632";
    // endregion

    // region Fields
    @SerializedName("job")
    public String job;
    @SerializedName("character")
    public String character;
    @SerializedName("name")
    public String name;
    @SerializedName("department")
    public String department;
    @SerializedName("profile_path")
    public String profilePath;

    private Palette profilePalette;
    // endregion

    // region Getters

    public String getJob() {
        return job;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public Palette getProfilePalette() {
        return profilePalette;
    }

    public String getProfileUrl(){
        String profileUrl = String.format("%s%s%s", SECURE_BASE_URL, PROFILE_SIZE, profilePath);
        return profileUrl;
    }

    // endregion

    // region Setters

    public void setJob(String job) {
        this.job = job;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void setProfilePalette(Palette profilePalette) {
        this.profilePalette = profilePalette;
    }

    // endregion
}
