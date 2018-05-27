package com.etiennelawlor.moviehub.presentation.models;

import java.util.Date;
import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class TelevisionShowsPresentationModel {

    // region Fields
    private List<TelevisionShowPresentationModel> televisionShows;
    private int pageNumber;
    private boolean isLastPage;
    private Date expiredAt;
    // endregion

    // region Constructors

    public TelevisionShowsPresentationModel() {
    }

    // endregion

    // region Getters

    public List<TelevisionShowPresentationModel> getTelevisionShows() {
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

    public void setTelevisionShows(List<TelevisionShowPresentationModel> televisionShows) {
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
    // endregion

    @Override
    public String toString() {
        return "TelevisionShowsPresentationModel{" +
                "televisionShows=" + televisionShows +
                ", pageNumber=" + pageNumber +
                ", isLastPage=" + isLastPage +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
