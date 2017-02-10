package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.PeopleEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowsEnvelope;

/**
 * Created by etiennelawlor on 12/19/16.
 */

public class FullSearchResponse {

    // region Member Variables
    private MoviesEnvelope moviesEnvelope;
    private TelevisionShowsEnvelope televisionShowsEnvelope;
    private PeopleEnvelope peopleEnvelope;
    // endregion

    // region Constructors

    public FullSearchResponse(MoviesEnvelope moviesEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope, PeopleEnvelope peopleEnvelope) {
        this.moviesEnvelope = moviesEnvelope;
        this.televisionShowsEnvelope = televisionShowsEnvelope;
        this.peopleEnvelope = peopleEnvelope;
    }

    // endregion

    // region Getters

    public MoviesEnvelope getMoviesEnvelope() {
        return moviesEnvelope;
    }

    public TelevisionShowsEnvelope getTelevisionShowsEnvelope() {
        return televisionShowsEnvelope;
    }

    public PeopleEnvelope getPeopleEnvelope() {
        return peopleEnvelope;
    }

    public boolean hasResults(){
        return (moviesEnvelope.getMovies() != null && moviesEnvelope.getMovies().size() > 0)
                || (televisionShowsEnvelope.getTelevisionShows() != null && televisionShowsEnvelope.getTelevisionShows().size() > 0)
                || (peopleEnvelope.getPersons() != null && peopleEnvelope.getPersons().size() > 0);
    }
    // endregion

    // region Setters

    public void setMoviesEnvelope(MoviesEnvelope moviesEnvelope) {
        this.moviesEnvelope = moviesEnvelope;
    }

    public void setTelevisionShowsEnvelope(TelevisionShowsEnvelope televisionShowsEnvelope) {
        this.televisionShowsEnvelope = televisionShowsEnvelope;
    }

    public void setPeopleEnvelope(PeopleEnvelope peopleEnvelope) {
        this.peopleEnvelope = peopleEnvelope;
    }

    // endregion
}
