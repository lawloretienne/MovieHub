package com.etiennelawlor.moviehub.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class NetworkPresentationModel implements Parcelable {

    // region Fields
    public int id;
    public String name;
    // endregion

    // region Constructors
    public NetworkPresentationModel() {
    }

    protected NetworkPresentationModel(Parcel in) {
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

    public static final Creator<NetworkPresentationModel> CREATOR = new Creator<NetworkPresentationModel>() {
        @Override
        public NetworkPresentationModel createFromParcel(Parcel source) {
            return new NetworkPresentationModel(source);
        }

        @Override
        public NetworkPresentationModel[] newArray(int size) {
            return new NetworkPresentationModel[size];
        }
    };

    @Override
    public String toString() {
        return "NetworkPresentationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
