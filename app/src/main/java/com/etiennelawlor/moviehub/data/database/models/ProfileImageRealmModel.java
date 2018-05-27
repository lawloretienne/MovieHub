package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileImageRealmModel extends RealmObject {

    // region Fields
    public float aspectRatio;
    public String filePath;
    public int height;
    public float voteAverage;
    public int voteCount;
    public int width;
    // endregion

    // region Getters

    public float getAspectRatio() {
        return aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getHeight() {
        return height;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getWidth() {
        return width;
    }

    // endregion

    // region Setters

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // endregion
}
