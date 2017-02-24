package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.model.MoviesModel;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSourceContract;
import com.etiennelawlor.moviehub.ui.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.ui.movies.MoviesUIContract;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenterTest {

    // region Member Variables
    @Mock
    private MoviesUIContract.View moviesView;
    @Mock
    private MoviesDataSourceContract.Repository moviesRepository;
    @Mock
    private Observable<MoviesModel> mockObservable;
    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    private MoviesModel moviesModel;
    private MoviesPresenter moviesPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        moviesPresenter = new MoviesPresenter(moviesView, moviesRepository);
    }

    // region Test Methods
    @Test
    public void onLoadPopularMovies_shouldShowError_whenFirstPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(0), 1, true);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.<MoviesModel>error(new IOException()));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).hideEmptyView();
        verify(moviesView).hideErrorView();
        verify(moviesView).showLoadingView();

        verify(moviesView).hideLoadingView();
        verify(moviesView).showErrorView();
    }

    @Test
    public void onLoadPopularMovies_shouldShowError_whenNextPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(0), 2, true);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.<MoviesModel>error(new IOException()));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).showLoadingFooter();

        verify(moviesView).showErrorFooter();
    }

    @Test
    public void onLoadPopularMovies_shouldShowEmpty_whenFirstPageHasNoMovies() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(0), 1, true);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.just(moviesModel));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).hideEmptyView();
        verify(moviesView).hideErrorView();
        verify(moviesView).showLoadingView();

        verify(moviesView).hideLoadingView();
        verify(moviesView).showEmptyView();
        verify(moviesView).setModel(moviesModel);
    }

    @Test
    public void onLoadPopularMovies_shouldNotAddMovies_whenNextPageHasNoMovies() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(0), 2, true);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.just(moviesModel));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).showLoadingFooter();

        verify(moviesView).removeFooter();
        verify(moviesView).setModel(moviesModel);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenFirstPageHasMoviesAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(5), 1, true);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.just(moviesModel));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).hideEmptyView();
        verify(moviesView).hideErrorView();
        verify(moviesView).showLoadingView();

        verify(moviesView).hideLoadingView();
        verify(moviesView).addMoviesToAdapter(moviesModel.getMovies());
        verify(moviesView).setModel(moviesModel);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenFirstPageHasMoviesAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(5), 1, false);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.just(moviesModel));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).hideEmptyView();
        verify(moviesView).hideErrorView();
        verify(moviesView).showLoadingView();

        verify(moviesView).hideLoadingView();
        verify(moviesView).addMoviesToAdapter(moviesModel.getMovies());
        verify(moviesView).addFooter();
        verify(moviesView).setModel(moviesModel);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenNextPageHasMoviesAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(5), 2, true);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.just(moviesModel));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).showLoadingFooter();

        verify(moviesView).removeFooter();
        verify(moviesView).addMoviesToAdapter(moviesModel.getMovies());
        verify(moviesView).setModel(moviesModel);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenNextPageHasMoviesAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        moviesModel = new MoviesModel(getListOfMovies(5), 2, false);
        when(moviesRepository.getPopularMovies(anyInt())).thenReturn(Observable.just(moviesModel));

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(moviesModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).showLoadingFooter();

        verify(moviesView).removeFooter();
        verify(moviesView).addMoviesToAdapter(moviesModel.getMovies());
        verify(moviesView).addFooter();
        verify(moviesView).setModel(moviesModel);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() throws Exception {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();

        // 2. (When) Then perform one or more actions
        moviesPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).openMovieDetails(movie);
    }

    @Test
    public void onScrollToEndOfList_shouldLoadMoreItems() throws Exception {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        moviesPresenter.onScrollToEndOfList();

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).loadMoreItems();
    }
    // endregion

    // region Helper Methods
    private List<Movie> getListOfMovies(int numOfMovies){
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<numOfMovies; i++){
            Movie movie = new Movie();
            movies.add(movie);
        }
        return movies;
    }
    // endregion
}
