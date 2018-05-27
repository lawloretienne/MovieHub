package com.etiennelawlor.moviehub.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenrePresentationModel implements Parcelable {

    // region Fields
    public int id;
    public String name;
    // endregion

    // region Constructors
    public GenrePresentationModel() {
    }

    protected GenrePresentationModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // endregion

    // region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }
    // endregion

    public static final Creator<GenrePresentationModel> CREATOR = new Creator<GenrePresentationModel>() {
        @Override
        public GenrePresentationModel createFromParcel(Parcel source) {
            return new GenrePresentationModel(source);
        }

        @Override
        public GenrePresentationModel[] newArray(int size) {
            return new GenrePresentationModel[size];
        }
    };

    @Override
    public String toString() {
        return "GenrePresentationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
