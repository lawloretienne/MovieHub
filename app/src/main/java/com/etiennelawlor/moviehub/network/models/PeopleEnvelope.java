package com.etiennelawlor.moviehub.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class PeopleEnvelope {

    // region Fields
    @SerializedName("page")
    public Integer page;
    @SerializedName("results")
    public List<Person> persons = null;
    @SerializedName("total_results")
    public Integer totalResults;
    @SerializedName("total_pages")
    public Integer totalPages;
    // endregion

    // region Getters

    public Integer getPage() {
        return page;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    // endregion

    // region Setters

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    // endregion
}
