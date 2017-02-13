package com.etiennelawlor.moviehub.ui.main;

import com.etiennelawlor.moviehub.ui.movies.MoviesContract;

/**
 * Created by etiennelawlor on 2/12/17.
 */

public class MainPresenter implements MainContract.Presenter {

    // region Member Variables
    private final MainContract.View mainView;
    // endregion

    // region Constructors
    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
    }
    // endregion

    // region MainContract.Presenter Methods
    @Override
    public void viewSearch() {
        mainView.viewSearch();
    }
    // endregion
}
