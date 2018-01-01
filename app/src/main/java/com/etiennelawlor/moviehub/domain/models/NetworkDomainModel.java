package com.etiennelawlor.moviehub.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkDomainModel implements Parcelable {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    // endregion

    // region Constructors
    public NetworkDomainModel() {
    }

    protected NetworkDomainModel(Parcel in) {
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

    public static final Creator<NetworkDomainModel> CREATOR = new Creator<NetworkDomainModel>() {
        @Override
        public NetworkDomainModel createFromParcel(Parcel source) {
            return new NetworkDomainModel(source);
        }

        @Override
        public NetworkDomainModel[] newArray(int size) {
            return new NetworkDomainModel[size];
        }
    };

    @Override
    public String toString() {
        return "NetworkResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
