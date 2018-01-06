package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class TelevisionShowDetailsDomainModel {

    // region Fields
    private TelevisionShowDomainModel televisionShow;
    private List<TelevisionShowCreditDomainModel> cast;
    private List<TelevisionShowCreditDomainModel> crew;
    private List<TelevisionShowDomainModel> similarTelevisionShows;
    private String rating;
    // endregion

    // region Constructors

    public TelevisionShowDetailsDomainModel() {
    }

    // endregion

    // region Getters


    public TelevisionShowDomainModel getTelevisionShow() {
        return televisionShow;
    }

    public List<TelevisionShowCreditDomainModel> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditDomainModel> getCrew() {
        return crew;
    }

    public List<TelevisionShowDomainModel> getSimilarTelevisionShows() {
        return similarTelevisionShows;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters

    public void setTelevisionShow(TelevisionShowDomainModel televisionShow) {
        this.televisionShow = televisionShow;
    }

    public void setCast(List<TelevisionShowCreditDomainModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditDomainModel> crew) {
        this.crew = crew;
    }

    public void setSimilarTelevisionShows(List<TelevisionShowDomainModel> similarTelevisionShows) {
        this.similarTelevisionShows = similarTelevisionShows;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowDetailsDomainModel{" +
                "televisionShow=" + televisionShow +
                ", cast=" + cast +
                ", crew=" + crew +
                ", similarTelevisionShows=" + similarTelevisionShows +
                ", rating='" + rating + '\'' +
                '}';
    }
}
