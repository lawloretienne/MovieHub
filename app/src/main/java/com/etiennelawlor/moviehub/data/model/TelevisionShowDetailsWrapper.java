package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowCredit;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class TelevisionShowDetailsWrapper {

    // region Member Variables
    private TelevisionShow televisionShow;
    private List<TelevisionShowCredit> cast;
    private List<TelevisionShowCredit> crew;
    private List<TelevisionShow> similarTelevisionShows;
    private String rating;
    // endregion

    // region Constructors

    public TelevisionShowDetailsWrapper(TelevisionShow televisionShow, List<TelevisionShowCredit> cast, List<TelevisionShowCredit> crew, List<TelevisionShow> similarTelevisionShows, String rating) {
        this.televisionShow = televisionShow;
        this.cast = cast;
        this.crew = crew;
        this.similarTelevisionShows = similarTelevisionShows;
        this.rating = rating;
    }

    // endregion

    // region Getters


    public TelevisionShow getTelevisionShow() {
        return televisionShow;
    }

    public List<TelevisionShowCredit> getCast() {
        return cast;
    }

    public List<TelevisionShowCredit> getCrew() {
        return crew;
    }

    public List<TelevisionShow> getSimilarTelevisionShows() {
        return similarTelevisionShows;
    }

    public String getRating() {
        return rating;
    }

    // endregion

    // region Setters


    public void setTelevisionShow(TelevisionShow televisionShow) {
        this.televisionShow = televisionShow;
    }

    public void setCast(List<TelevisionShowCredit> cast) {
        this.cast = cast;
    }

    public void setCrew(List<TelevisionShowCredit> crew) {
        this.crew = crew;
    }

    public void setSimilarTelevisionShows(List<TelevisionShow> similarTelevisionShows) {
        this.similarTelevisionShows = similarTelevisionShows;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // endregion
}
