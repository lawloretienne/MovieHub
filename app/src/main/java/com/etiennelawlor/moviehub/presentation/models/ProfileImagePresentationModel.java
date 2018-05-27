package com.etiennelawlor.moviehub.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagePresentationModel implements Parcelable {

    // region Fields
    public float aspectRatio;
    public String filePath;
    public int height;
    public float voteAverage;
    public int voteCount;
    public int width;
    // endregion

    // region Constructors
    public ProfileImagePresentationModel() {
    }

    protected ProfileImagePresentationModel(Parcel in) {
        this.aspectRatio = in.readFloat();
        this.filePath = in.readString();
        this.height = in.readInt();
        this.voteAverage = in.readFloat();
        this.voteCount = in.readInt();
        this.width = in.readInt();
    }
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

    // region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.aspectRatio);
        dest.writeString(this.filePath);
        dest.writeInt(this.height);
        dest.writeFloat(this.voteAverage);
        dest.writeInt(this.voteCount);
        dest.writeInt(this.width);
    }
    // endregion

    public static final Creator<ProfileImagePresentationModel> CREATOR = new Creator<ProfileImagePresentationModel>() {
        @Override
        public ProfileImagePresentationModel createFromParcel(Parcel source) {
            return new ProfileImagePresentationModel(source);
        }

        @Override
        public ProfileImagePresentationModel[] newArray(int size) {
            return new ProfileImagePresentationModel[size];
        }
    };

    @Override
    public String toString() {
        return "ProfileImagePresentationModel{" +
                "aspectRatio=" + aspectRatio +
                ", filePath='" + filePath + '\'' +
                ", height=" + height +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", width=" + width +
                '}';
    }
}
