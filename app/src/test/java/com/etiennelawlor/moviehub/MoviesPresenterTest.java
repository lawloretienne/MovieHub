package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.model.MoviesModel;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.source.movies.MoviesDataSourceContract;
import com.etiennelawlor.moviehub.ui.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.ui.movies.MoviesUIContract;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;

import static org.mockito.Matchers.anyInt;
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

    @Captor
    private ArgumentCaptor<Subscriber<MoviesModel>> argumentCapture;

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
        moviesModel = new MoviesModel(new ArrayList<Movie>(), 0, false);

    }

    // Test method names
    // methodName_codepathExplanation
    // methodName_StateUnderTest_ExpectedBehavior
    // methodName_InputValues_ExpectedBehavior
    // methodName_WithCertainState_ShouldDoSomething
    // methodName_WhenX_ExpectY

    @Test
    public void loadMovies_whenX_expectY(){

//        moviesPresenter.loadMovies();
//        verify(moviesView)

    }

//    void onLoadPopularMovies(int currentPage);
//    void onMovieClick(Movie movie);
//    void onScrollToEndOfList();
//    void onDestroyView();

    @Test
    public void onLoadPopularMovies_shouldShowError_whenFirstPageRequestFailed() {
        // 1. Set up conditions required for the test
        int currentPage = 1;
        when(moviesRepository.getPopularMovies(currentPage)).thenReturn(mockObservable);
        // 2. Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(currentPage);
        // 3. Afterwards, verify that the state you are expecting is actually achieved
        verify(moviesView).hideLoadingView();
        verify(moviesView).showErrorView();
//        moviesPresenter.onLoadPopularMovies(anyInt());
    }

    @Test
    public void onLoadPopularMovies_shouldShowEmpty_whenFirstPagResponseIsEmpty() {
        // 1. Set up conditions required for the test

        // 2. Then perform one or more actions
        int currentPage = 1;
        moviesPresenter.onLoadPopularMovies(currentPage);

        // 3. Afterwards, verify that the state you are expecting is actually achieved
        when(moviesRepository.getPopularMovies(currentPage)).thenReturn(Observable.just(moviesModel));

        verify(moviesView).hideEmptyView();
        verify(moviesView).hideErrorView();
        verify(moviesView).showLoadingView();

        verify(moviesView).hideLoadingView();
        verify(moviesView).showEmptyView();
//        moviesPresenter.onLoadPopularMovies(anyInt());
    }

    @Test
    public void onLoadPopularMovies_shouldShowContent_whenFirstPagResponseIsSuccessful() {
        // 1. Set up conditions required for the test
        int currentPage = 1;
        when(moviesRepository.getPopularMovies(currentPage)).thenReturn(mockObservable);
        // 2. Then perform one or more actions
        moviesPresenter.onLoadPopularMovies(currentPage);
        // 3. Afterwards, verify that the state you are expecting is actually achieved
        verify(mockObservable).subscribe(argumentCapture.capture());
        argumentCapture.getValue().onNext(moviesModel);

        verify(moviesView).hideLoadingView();
        verify(moviesView).addMoviesToAdapter(moviesModel.getMovies());
//        moviesPresenter.onLoadPopularMovies(anyInt());
    }
}
