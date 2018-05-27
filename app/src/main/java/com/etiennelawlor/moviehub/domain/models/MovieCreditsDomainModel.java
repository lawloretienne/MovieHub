package com.etiennelawlor.moviehub.domain.models;

import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieCreditsDomainModel {

    // region Fields
    public int id;
    public List<MovieCreditDomainModel> cast = null;
    public List<MovieCreditDomainModel> crew = null;
    private Date expiredAt;
    // endregion

    // region Constructors
    public MovieCreditsDomainModel() {
    }
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public List<MovieCreditDomainModel> getCast() {
        return cast;
    }

    public List<MovieCreditDomainModel> getCrew() {
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

    public void setCast(List<MovieCreditDomainModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<MovieCreditDomainModel> crew) {
        this.crew = crew;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    @Override
    public String toString() {
        return "MovieCreditsDomainModel{" +
                "id=" + id +
                ", cast=" + cast +
                ", crew=" + crew +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
