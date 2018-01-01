package com.etiennelawlor.moviehub.data.repositories.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenreDataModel implements Parcelable {

    // region Fields
    public int id;
    public String name;
    // endregion

    // region Constructors
    public GenreDataModel() {
    }

    protected GenreDataModel(Parcel in) {
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

    public static final Parcelable.Creator<GenreDataModel> CREATOR = new Parcelable.Creator<GenreDataModel>() {
        @Override
        public GenreDataModel createFromParcel(Parcel source) {
            return new GenreDataModel(source);
        }

        @Override
        public GenreDataModel[] newArray(int size) {
            return new GenreDataModel[size];
        }
    };

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
