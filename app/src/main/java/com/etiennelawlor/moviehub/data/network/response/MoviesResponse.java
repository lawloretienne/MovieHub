package com.etiennelawlor.moviehub.data.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class MoviesResponse {

    // region Fields
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<Movie> movies = null;
    @SerializedName("total_results")
    public int totalResults;
    @SerializedName("total_pages")
    public int totalPages;
    // endregion

    // region Getters

    public int getPage() {
        return page;
    }

    public List<Movie> getMovies() {
        return movies;
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

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
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
        return "MoviesResponse{" +
                "page=" + page +
                ", movies=" + movies +
                ", totalResults=" + totalResults +
                ", totalPages=" + totalPages +
                '}';
    }
}
