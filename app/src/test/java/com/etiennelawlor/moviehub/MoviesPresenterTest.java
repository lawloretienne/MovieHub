package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.model.MoviesWrapper;
import com.etiennelawlor.moviehub.data.model.PagingInfo;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSourceContract;
import com.etiennelawlor.moviehub.ui.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.ui.movies.MoviesUiContract;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerTransformer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private MoviesUiContract.View mockMoviesView;
    @Mock
    private MoviesDataSourceContract.Repository mockMoviesRepository;

    // Stubs
    private Observable stub;

    // endregion

    // region Member Variables
    private MoviesWrapper moviesWrapper;
    private MoviesPresenter moviesPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        moviesPresenter = new MoviesPresenter(mockMoviesView, mockMoviesRepository, new TestSchedulerTransformer<MoviesWrapper>());
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadPopularMovies_shouldShowError_whenFirstPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(1, true);
        moviesWrapper = new MoviesWrapper(getListOfMovies(0), pagingInfo);
        stub = Observable.<MoviesWrapper>error(new IOException());
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).hideEmptyView();
        verify(mockMoviesView).hideErrorView();
        verify(mockMoviesView).showLoadingView();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).hideLoadingView();
        verify(mockMoviesView).setErrorText(anyString());
        verify(mockMoviesView).showErrorView();
    }

    @Test
    public void onLoadPopularMovies_shouldShowError_whenNextPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(2, true);
        moviesWrapper = new MoviesWrapper(getListOfMovies(0), pagingInfo);
        stub = Observable.<MoviesWrapper>error(new IOException());
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).showLoadingFooter();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).showErrorFooter();
    }

    @Test
    public void onLoadPopularMovies_shouldShowEmpty_whenFirstPageHasNoMovies() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(1, true);
        moviesWrapper = new MoviesWrapper(getListOfMovies(0), pagingInfo);
        stub = Observable.just(moviesWrapper);
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).hideEmptyView();
        verify(mockMoviesView).hideErrorView();
        verify(mockMoviesView).showLoadingView();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).hideLoadingView();
        verify(mockMoviesView).showEmptyView();
        verify(mockMoviesView).setPagingInfo(pagingInfo);
    }

    @Test
    public void onLoadPopularMovies_shouldNotAddMovies_whenNextPageHasNoMovies() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(2, true);
        moviesWrapper = new MoviesWrapper(getListOfMovies(0), pagingInfo);
        stub = Observable.just(moviesWrapper);
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).showLoadingFooter();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).removeFooter();
        verify(mockMoviesView).setPagingInfo(pagingInfo);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenFirstPageHasMoviesAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(1, true);
        moviesWrapper = new MoviesWrapper(getListOfMovies(5), pagingInfo);
        stub = Observable.just(moviesWrapper);
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).hideEmptyView();
        verify(mockMoviesView).hideErrorView();
        verify(mockMoviesView).showLoadingView();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).hideLoadingView();
        verify(mockMoviesView).addHeader();
        verify(mockMoviesView).addMoviesToAdapter(moviesWrapper.getMovies());
        verify(mockMoviesView).setPagingInfo(pagingInfo);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenFirstPageHasMoviesAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(1, false);
        moviesWrapper = new MoviesWrapper(getListOfMovies(5), pagingInfo);
        stub = Observable.just(moviesWrapper);
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).hideEmptyView();
        verify(mockMoviesView).hideErrorView();
        verify(mockMoviesView).showLoadingView();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).hideLoadingView();
        verify(mockMoviesView).addHeader();
        verify(mockMoviesView).addMoviesToAdapter(moviesWrapper.getMovies());
        verify(mockMoviesView).addFooter();
        verify(mockMoviesView).setPagingInfo(pagingInfo);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenNextPageHasMoviesAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(2, true);
        moviesWrapper = new MoviesWrapper(getListOfMovies(5), pagingInfo);
        stub = Observable.just(moviesWrapper);
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).showLoadingFooter();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).removeFooter();
        verify(mockMoviesView).addMoviesToAdapter(moviesWrapper.getMovies());
        verify(mockMoviesView).setPagingInfo(pagingInfo);
    }

    @Test
    public void onLoadPopularMovies_shouldAddMovies_whenNextPageHasMoviesAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        PagingInfo pagingInfo = new PagingInfo(2, false);
        moviesWrapper = new MoviesWrapper(getListOfMovies(5), pagingInfo);
        stub = Observable.just(moviesWrapper);
        when(mockMoviesRepository.getPopularMovies(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(pagingInfo.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).showLoadingFooter();

        verify(mockMoviesRepository).getPopularMovies(anyInt());

        verify(mockMoviesView).removeFooter();
        verify(mockMoviesView).addMoviesToAdapter(moviesWrapper.getMovies());
        verify(mockMoviesView).addFooter();
        verify(mockMoviesView).setPagingInfo(pagingInfo);
//        verify(mockMoviesView, times(1)).setModel(any(MoviesWrapper.class)); // Alternative verify check
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();

        // 2. (When) Then perform one or more actions
        moviesPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).openMovieDetails(movie);

        verifyZeroInteractions(mockMoviesRepository);
    }

    @Test
    public void onScrollToEndOfList_shouldLoadMoreItems() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        moviesPresenter.onScrollToEndOfList();

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMoviesView).loadMoreItems();

        verifyZeroInteractions(mockMoviesRepository);
    }

    @Test
    public void onDestroyView_shouldNotHaveInteractions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        moviesPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockMoviesView);
        verifyZeroInteractions(mockMoviesRepository);
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
