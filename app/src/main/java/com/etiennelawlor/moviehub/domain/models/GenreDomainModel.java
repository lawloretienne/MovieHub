package com.etiennelawlor.moviehub.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class GenreDomainModel implements Parcelable {

    // region Fields
    public int id;
    public String name;
    // endregion

    // region Constructors
    public GenreDomainModel() {
    }

    protected GenreDomainModel(Parcel in) {
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

    public static final Creator<GenreDomainModel> CREATOR = new Creator<GenreDomainModel>() {
        @Override
        public GenreDomainModel createFromParcel(Parcel source) {
            return new GenreDomainModel(source);
        }

        @Override
        public GenreDomainModel[] newArray(int size) {
            return new GenreDomainModel[size];
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
