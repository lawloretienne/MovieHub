package com.etiennelawlor.moviehub.presentation.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieCreditsPresentationModel {

    // region Fields
    public int id;
    public List<MovieCreditPresentationModel> cast = null;
    public List<MovieCreditPresentationModel> crew = null;
    private Date expiredAt;
    // endregion

    // region Constructors
    public MovieCreditsPresentationModel(int id, List<MovieCreditPresentationModel> cast, List<MovieCreditPresentationModel> crew, Date expiredAt) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
        this.expiredAt = expiredAt;
    }

    public MovieCreditsPresentationModel() {
    }
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieCreditPresentationModel> getCast() {
        return cast;
    }

    public List<MovieCreditPresentationModel> getCrew() {
        return crew;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<MovieCreditPresentationModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditPresentationModel> crew) {
        this.crew = crew;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    // Helper Methods
    public boolean isExpired() {
        return Calendar.getInstance().getTime().getTime() > expiredAt.getTime();
    }
    // endregion

    @Override
    public String toString() {
        return "MovieCreditsPresentationModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
