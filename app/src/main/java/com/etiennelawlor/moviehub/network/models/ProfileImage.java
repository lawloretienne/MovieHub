package com.etiennelawlor.moviehub.network.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class ProfileImage implements Parcelable {

    // region Fields
    @SerializedName("aspect_ratio")
    public Float aspectRatio;
    @SerializedName("file_path")
    public String filePath;
    @SerializedName("height")
    public Integer height;
    @SerializedName("vote_average")
    public Float voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;
    @SerializedName("width")
    public Integer width;
    // endregion

    // region Constructors
    public ProfileImage() {
    }

    protected ProfileImage(Parcel in) {
        this.aspectRatio = (Float) in.readValue(Float.class.getClassLoader());
        this.filePath = in.readString();
        this.height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.voteAverage = (Float) in.readValue(Float.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.width = (Integer) in.readValue(Integer.class.getClassLoader());
    }
    // endregion

    // region Getters

    public Float getAspectRatio() {
        return aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getHeight() {
        return height;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Integer getWidth() {
        return width;
    }

    // endregion

    // region Setters

    public void setAspectRatio(Float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void setWidth(Integer width) {
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
        dest.writeValue(this.aspectRatio);
        dest.writeString(this.filePath);
        dest.writeValue(this.height);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.width);
    }
    // endregion

    public static final Parcelable.Creator<ProfileImage> CREATOR = new Parcelable.Creator<ProfileImage>() {
        @Override
        public ProfileImage createFromParcel(Parcel source) {
            return new ProfileImage(source);
        }

        @Override
        public ProfileImage[] newArray(int size) {
            return new ProfileImage[size];
        }
    };
}
