package com.etiennelawlor.moviehub.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowsEnvelope {

    // region Fields
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<TelevisionShow> televisionShows = null;
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;
    // endregion

    // region Getters

    public int getPage() {
        return page;
    }

    public List<TelevisionShow> getTelevisionShows() {
        return televisionShows;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    // endregion

    // region Setters

    public void setPage(int page) {
        this.page = page;
    }

    public void setTelevisionShows(List<TelevisionShow> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    // endregion
}
