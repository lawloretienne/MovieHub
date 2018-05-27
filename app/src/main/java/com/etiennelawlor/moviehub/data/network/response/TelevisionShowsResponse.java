package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowsResponse {

    // region Fields
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<TelevisionShowResponse> televisionShows = null;
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;
    // endregion

    // region Getters

    public int getPage() {
        return page;
    }

    public List<TelevisionShowResponse> getTelevisionShows() {
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

    public void setTelevisionShows(List<TelevisionShowResponse> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowsResponse{" +
                "page=" + page +
                ", televisionShows=" + televisionShows +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}';
    }
}
