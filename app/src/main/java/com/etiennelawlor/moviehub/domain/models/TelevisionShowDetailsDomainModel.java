package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditResponse;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class TelevisionShowDetailsDomainModel {

    // region Member Variables
    private TelevisionShowResponse televisionShow;
    private List<TelevisionShowCreditResponse> cast;
    private List<TelevisionShowCreditResponse> crew;
    private List<TelevisionShowResponse> similarTelevisionShows;
    private String rating;
    // endregion

    // region Constructors

    public TelevisionShowDetailsDomainModel(TelevisionShowResponse televisionShow, List<TelevisionShowCreditResponse> cast, List<TelevisionShowCreditResponse> crew, List<TelevisionShowResponse> similarTelevisionShows, String rating) {
        this.televisionShow = televisionShow;
        this.cast = cast;
        this.crew = crew;
        this.similarTelevisionShows = similarTelevisionShows;
        this.rating = rating;
    }

    // endregion

    // region Getters


    public TelevisionShowResponse getTelevisionShow() {
        return televisionShow;
    }

    public List<TelevisionShowCreditResponse> getCast() {
        return cast;
    }

    public List<TelevisionShowCreditResponse> getCrew() {
        return crew;
    }

    public List<TelevisionShowResponse> getSimilarTelevisionShows() {
        return similarTelevisionShows;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters


    public void setTelevisionShow(TelevisionShowResponse televisionShow) {
        this.televisionShow = televisionShow;
    }

    public void setCast(List<TelevisionShowCreditResponse> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCreditResponse> crew) {
        this.crew = crew;
    }

    public void setSimilarTelevisionShows(List<TelevisionShowResponse> similarTelevisionShows) {
        this.similarTelevisionShows = similarTelevisionShows;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion
}
