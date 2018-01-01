package com.etiennelawlor.moviehub.data.repositories.models;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class TelevisionShowsDataModel {

    // region Member Variables
    private List<TelevisionShowResponse> televisionShows;
    private int pageNumber;
    private boolean isLastPage;
    private Date expiredAt;
    // endregion

    // region Constructors

    public TelevisionShowsDataModel(List<TelevisionShowResponse> televisionShows, int pageNumber, boolean isLastPage, Date expiredAt) {
        this.televisionShows = televisionShows;
        this.pageNumber = pageNumber;
        this.isLastPage = isLastPage;
        this.expiredAt = expiredAt;
    }

    public TelevisionShowsDataModel() {
    }

    // endregion

    // region Getters

    public List<TelevisionShowResponse> getTelevisionShows() {
        return televisionShows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    // endregion

    // region Setters

    public void setTelevisionShows(List<TelevisionShowResponse> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    // endregion

    // Helper Methods
    public boolean hasTelevisionShows() { return televisionShows.size() > 0;}

    public void incrementPageNumber() { this.pageNumber += 1; }

    public boolean isExpired() {
        return Calendar.getInstance().getTime().getTime() > expiredAt.getTime();
    }
    // endregion
}
