package com.etiennelawlor.moviehub.presentation.models;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class CreditPresentationModel {

    // region Fields
    public int id;
    public String creditId;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public String getCreditId() {
        return creditId;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    // endregion


    @Override
    public String toString() {
        return "CreditPresentationModel{" +
                "id=" + id +
                ", creditId='" + creditId + '\'' +
                '}';
    }
}
