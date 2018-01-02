package com.etiennelawlor.moviehub.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImageDomainModel implements Parcelable {

    // region Fields
    @SerializedName("aspect_ratio")
    public float aspectRatio;
    @SerializedName("file_path")
    public String filePath;
    @SerializedName("height")
    public int height;
    @SerializedName("vote_average")
    public float voteAverage;
    @SerializedName("vote_count")
    public int voteCount;
    @SerializedName("width")
    public int width;
    // endregion

    // region Constructors
    public ProfileImageDomainModel() {
    }

    protected ProfileImageDomainModel(Parcel in) {
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

    public static final Creator<ProfileImageDomainModel> CREATOR = new Creator<ProfileImageDomainModel>() {
        @Override
        public ProfileImageDomainModel createFromParcel(Parcel source) {
            return new ProfileImageDomainModel(source);
        }

        @Override
        public ProfileImageDomainModel[] newArray(int size) {
            return new ProfileImageDomainModel[size];
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
