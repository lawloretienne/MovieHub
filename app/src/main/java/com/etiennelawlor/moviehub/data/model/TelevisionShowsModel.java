package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class TelevisionShowsModel {

    // region Member Variables
    private List<TelevisionShow> televisionShows;
    private int currentPage;
    private boolean isLastPage;
    // endregion

    // region Constructors

    public TelevisionShowsModel(List<TelevisionShow> televisionShows, int currentPage, boolean isLastPage) {
        this.televisionShows = televisionShows;
        this.currentPage = currentPage;
        this.isLastPage = isLastPage;
    }

    // endregion

    // region Getters


    public List<TelevisionShow> getTelevisionShows() {
        return televisionShows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    // endregion

    // region Setters

    public void setTelevisionShows(List<TelevisionShow> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    // endregion

    // Helper Methods
    public void incrementPage() { this.currentPage += 1; }

    public boolean hasTelevisionShows() { return televisionShows.size() > 0;}
    // endregion
}
