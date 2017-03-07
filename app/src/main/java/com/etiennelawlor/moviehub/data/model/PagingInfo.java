package com.etiennelawlor.moviehub.data.model;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class PagingInfo {

    // region Member Variables
    private int currentPage;
    private boolean isLastPage;
    // endregion

    // region Constructors

    public PagingInfo(int currentPage, boolean isLastPage) {
        this.currentPage = currentPage;
        this.isLastPage = isLastPage;
    }

    // endregion

    // region Getters

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    // endregion

    // region Setters

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    // endregion

    // Helper Methods
    public void incrementPage() { this.currentPage += 1; }
    // endregion
}
