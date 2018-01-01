package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class SearchDomainModel {

    // region Member Variables
    private String query;
    private List<MovieResponse> movies;
    private List<TelevisionShowResponse> televisionShows;
    private List<PersonResponse> persons;
    // endregion

    // region Constructors

    public SearchDomainModel(String query, List<MovieResponse> movies, List<TelevisionShowResponse> televisionShows, List<PersonResponse> persons) {
        this.query = query;
        this.movies = movies;
        this.televisionShows = televisionShows;
        this.persons = persons;
    }

    // endregion

    // region Getters

    public String getQuery() {
        return query;
    }

    public List<MovieResponse> getMovies() {
        return movies;
    }

    public List<TelevisionShowResponse> getTelevisionShows() {
        return televisionShows;
    }

    public List<PersonResponse> getPersons() {
        return persons;
    }

    // endregion

    // region Setters

    public void setQuery(String query) {
        this.query = query;
    }

    public void setMovies(List<MovieResponse> movies) {
        this.movies = movies;
    }

    public void setTelevisionShows(List<TelevisionShowResponse> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setPersons(List<PersonResponse> persons) {
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
}
