package com.etiennelawlor.moviehub.presentation.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class SearchPresentationModel {

    // region Fields
    private String query;
    private List<MoviePresentationModel> movies;
    private List<TelevisionShowPresentationModel> televisionShows;
    private List<PersonPresentationModel> persons;
    // endregion

    // region Constructors
    public SearchPresentationModel() {
    }
    // endregion

    // region Getters

    public String getQuery() {
        return query;
    }

    public List<MoviePresentationModel> getMovies() {
        return movies;
    }

    public List<TelevisionShowPresentationModel> getTelevisionShows() {
        return televisionShows;
    }

    public List<PersonPresentationModel> getPersons() {
        return persons;
    }

    // endregion

    // region Setters

    public void setQuery(String query) {
        this.query = query;
    }

    public void setMovies(List<MoviePresentationModel> movies) {
        this.movies = movies;
    }

    public void setTelevisionShows(List<TelevisionShowPresentationModel> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setPersons(List<PersonPresentationModel> persons) {
        this.persons = persons;
    }

    // endregion

    // region Helper Methods
    public boolean hasMovies() {
        return movies != null && movies.size() > 0;
    }

    public boolean hasTelevisionShows() {
        return televisionShows != null && televisionShows.size() > 0;
    }

    public boolean hasPersons() {
        return persons != null && persons.size() > 0;
    }

    public boolean hasResults(){
        return (hasMovies())
                || (hasTelevisionShows())
                || (hasPersons());
    }
    // endregion

    @Override
    public String toString() {
        return "SearchPresentationModel{" +
                "query='" + query + '\'' +
                ", movies=" + movies +
                ", televisionShows=" + televisionShows +
                ", persons=" + persons +
                '}';
    }
}
