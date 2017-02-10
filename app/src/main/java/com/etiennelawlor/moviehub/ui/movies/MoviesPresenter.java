package com.etiennelawlor.moviehub.ui.movies;

import com.etiennelawlor.moviehub.data.repository.MoviesRepository;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesContract.Presenter {

    // region Fields
    private final MoviesRepository moviesRepository;
    private final MoviesContract.View moviesView;
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesRepository moviesRepository, MoviesContract.View moviesView) {
        this.moviesRepository = moviesRepository;
        this.moviesView = moviesView;
    }
    // endregion

    @Override
    public void loadMovies(PageType pageType) {
        switch (pageType){
            case FIRST_PAGE:
                moviesRepository.getPopularMovies(0);
                break;
            case NEXT_PAGE:
                moviesRepository.getPopularMovies(1);
                break;
            default:
                break;
        }
    }

    @Override
    public void reloadMovies(PageType pageType) {
        switch (pageType){
            case FIRST_PAGE:
                moviesView.hideEmptyView();
                moviesView.hideErrorView();
                moviesView.showLoadingView();

//                moviesRepository.getFirstPage();
                moviesRepository.getPopularMovies(0);
                break;
            case NEXT_PAGE:
                moviesView.updateFooter(MoviesContract.View.FooterType.LOAD_MORE);

//                moviesRepository.getNextPage();
                moviesRepository.getPopularMovies(1);
                break;
            default:
                break;
        }
    }
}
