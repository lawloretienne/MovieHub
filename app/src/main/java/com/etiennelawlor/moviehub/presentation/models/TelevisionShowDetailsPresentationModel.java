package com.etiennelawlor.moviehub.presentation.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class TelevisionShowDetailsPresentationModel {

    // region Fields
    private TelevisionShowPresentationModel televisionShow;
    private List<TelevisionShowCreditPresentationModel> cast;
    private List<TelevisionShowCreditPresentationModel> crew;
    private List<TelevisionShowPresentationModel> similarTelevisionShows;
    private String rating;
    // endregion

    // region Constructors

    public TelevisionShowDetailsPresentationModel() {
    }

    // endregion

    // region Getters


    public TelevisionShowPresentationModel getTelevisionShow() {
        return televisionShow;
    }

    public List<TelevisionShowCreditPresentationModel> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditPresentationModel> getCrew() {
        return crew;
    }

    public List<TelevisionShowPresentationModel> getSimilarTelevisionShows() {
        return similarTelevisionShows;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters


    public void setTelevisionShow(TelevisionShowPresentationModel televisionShow) {
        this.televisionShow = televisionShow;
    }

    public void setCast(List<TelevisionShowCreditPresentationModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditPresentationModel> crew) {
        this.crew = crew;
    }

    public void setSimilarTelevisionShows(List<TelevisionShowPresentationModel> similarTelevisionShows) {
        this.similarTelevisionShows = similarTelevisionShows;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion


    @Override
    public String toString() {
        return "TelevisionShowDetailsPresentationModel{" +
                "televisionShow=" + televisionShow +
                ", cast=" + cast +
                ", crew=" + crew +
                ", similarTelevisionShows=" + similarTelevisionShows +
                ", rating='" + rating + '\'' +
                '}';
    }
}
