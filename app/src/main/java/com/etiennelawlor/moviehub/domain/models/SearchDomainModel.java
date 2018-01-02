package com.etiennelawlor.moviehub.domain.models;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class SearchDomainModel {

    // region Member Variables
    private String query;
    private List<MovieDomainModel> movies;
    private List<TelevisionShowDomainModel> televisionShows;
    private List<PersonDomainModel> persons;
    // endregion

    // region Constructors
    public SearchDomainModel() {
    }
    // endregion

    // region Getters

    public String getQuery() {
        return query;
    }

    public List<MovieDomainModel> getMovies() {
        return movies;
    }

    public List<TelevisionShowDomainModel> getTelevisionShows() {
        return televisionShows;
    }

    public List<PersonDomainModel> getPersons() {
        return persons;
    }

    // endregion

    // region Setters

    public void setQuery(String query) {
        this.query = query;
    }

    public void setMovies(List<MovieDomainModel> movies) {
        this.movies = movies;
    }

    public void setTelevisionShows(List<TelevisionShowDomainModel> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setPersons(List<PersonDomainModel> persons) {
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
        return "SearchDomainModel{" +
                "query='" + query + '\'' +
                ", movies=" + movies +
                ", televisionShows=" + televisionShows +
                ", persons=" + persons +
                '}';
    }
}
