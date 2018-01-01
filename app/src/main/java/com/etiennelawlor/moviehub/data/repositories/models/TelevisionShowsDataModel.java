package com.etiennelawlor.moviehub.data.repositories.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class TelevisionShowsDataModel {

    // region Member Variables
    private List<TelevisionShowDataModel> televisionShows;
    private int pageNumber;
    private boolean isLastPage;
    private Date expiredAt;
    // endregion

    // region Constructors

    public TelevisionShowsDataModel(List<TelevisionShowDataModel> televisionShows, int pageNumber, boolean isLastPage, Date expiredAt) {
        this.televisionShows = televisionShows;
        this.pageNumber = pageNumber;
        this.isLastPage = isLastPage;
        this.expiredAt = expiredAt;
    }

    public TelevisionShowsDataModel() {
    }

    // endregion

    // region Getters

    public List<TelevisionShowDataModel> getTelevisionShows() {
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

    public void setTelevisionShows(List<TelevisionShowDataModel> televisionShows) {
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
