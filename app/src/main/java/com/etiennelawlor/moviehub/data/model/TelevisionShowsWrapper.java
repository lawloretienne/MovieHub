package com.etiennelawlor.moviehub.data.model;

import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

import java.util.List;

/**
 * Created by etiennelawlor on 2/20/17.
 */

public class TelevisionShowsWrapper {

    // region Member Variables
    private List<TelevisionShow> televisionShows;
    private PagingInfo pagingInfo;
    // endregion

    // region Constructors

    public TelevisionShowsWrapper(List<TelevisionShow> televisionShows, PagingInfo pagingInfo) {
        this.televisionShows = televisionShows;
        this.pagingInfo = pagingInfo;
    }

    // endregion

    // region Getters


    public List<TelevisionShow> getTelevisionShows() {
        return televisionShows;
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    // endregion

    // region Setters

    public void setTelevisionShows(List<TelevisionShow> televisionShows) {
        this.televisionShows = televisionShows;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    // endregion

    // Helper Methods
    public boolean hasTelevisionShows() { return televisionShows.size() > 0;}
    // endregion
}
