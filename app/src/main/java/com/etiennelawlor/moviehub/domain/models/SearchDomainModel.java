package com.etiennelawlor.moviehub.domain.models;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;

import java.util.List;

/**
 * Created by etiennelawlor on 3/4/17.
 */

public class SearchDomainModel {

    // region Member Variables
    private String query;
    private List<Movie> movies;
    private List<TelevisionShow> televisionShows;
    private List<Person> persons;
    // endregion

    // region Constructors

    public SearchDomainModel(String query, List<Movie> movies, List<TelevisionShow> televisionShows, List<Person> persons) {
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

    public List<Movie> getMovies() {
        return movies;
    }

    public List<TelevisionShow> getTelevisionShows() {
        return televisionShows;
    }

    public List<Person> getPersons() {
        return persons;
    }

    // endregion

    // region Setters

    public void setQuery(String query) {
        this.query = query;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setTelevisionShows(List<TelevisionShow> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setPersons(List<Person> persons) {
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
