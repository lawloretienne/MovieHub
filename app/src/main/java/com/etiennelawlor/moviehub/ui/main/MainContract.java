package com.etiennelawlor.moviehub.ui.main;

import com.etiennelawlor.moviehub.data.remote.response.Movie;

/**
 * Created by etiennelawlor on 2/12/17.
 */

public interface MainContract {

    interface View {
        void viewSearch();
    }

    interface Presenter {
        void viewSearch();
    }
}
