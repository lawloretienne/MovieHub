package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class TelevisionShowDetailsDomainModel {

    // region Member Variables
    private TelevisionShowDataModel televisionShow;
    private List<TelevisionShowCreditDataModel> cast;
    private List<TelevisionShowCreditDataModel> crew;
    private List<TelevisionShowDataModel> similarTelevisionShows;
    private String rating;
    // endregion

    // region Constructors

    public TelevisionShowDetailsDomainModel() {
    }

    // endregion

    // region Getters


    public TelevisionShowDataModel getTelevisionShow() {
        return televisionShow;
    }

    public List<TelevisionShowCreditDataModel> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditDataModel> getCrew() {
        return crew;
    }

    public List<TelevisionShowDataModel> getSimilarTelevisionShows() {
        return similarTelevisionShows;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters


    public void setTelevisionShow(TelevisionShowDataModel televisionShow) {
        this.televisionShow = televisionShow;
    }

    public void setCast(List<TelevisionShowCreditDataModel> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditDataModel> crew) {
        this.crew = crew;
    }

    public void setSimilarTelevisionShows(List<TelevisionShowDataModel> similarTelevisionShows) {
        this.similarTelevisionShows = similarTelevisionShows;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion
}
