package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCredit;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.domain.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.MoviesDomainContract;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerTransformer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private MovieDetailsUiContract.View mockMovieDetailsView;
    @Mock
    private MovieDetailsDomainContract.UseCase mockMovieDetailsUseCase;

    // Stubs
    private ArgumentCaptor<Subscriber> subscriberArgumentCaptor;

    // endregion

    // region Member Variables
    private MovieDetailsWrapper movieDetailsWrapper;
    private MovieDetailsPresenter movieDetailsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        movieDetailsPresenter = new MovieDetailsPresenter(mockMovieDetailsView, mockMovieDetailsUseCase);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadMovieDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();
        movie.setId(1);
        List<MovieCredit> cast = new ArrayList<>();
        List<MovieCredit> crew = new ArrayList<>();
        List<Movie> similarMovies = new ArrayList<>();
        String rating = "";
        movieDetailsWrapper = new MovieDetailsWrapper(movie, cast, crew, similarMovies, rating);

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movie.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(mockMovieDetailsUseCase).getMovieDetails(anyInt(), subscriberArgumentCaptor.capture());
        subscriberArgumentCaptor.getValue().onError(new UnknownHostException());

        verify(mockMovieDetailsView).showErrorView();
    }

    @Test
    public void onLoadMovieDetails_shouldShowMovieDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();
        movie.setId(1);
        List<MovieCredit> cast = new ArrayList<>();
        List<MovieCredit> crew = new ArrayList<>();
        List<Movie> similarMovies = new ArrayList<>();
        String rating = "";
        movieDetailsWrapper = new MovieDetailsWrapper(movie, cast, crew, similarMovies, rating);

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movie.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(mockMovieDetailsUseCase).getMovieDetails(anyInt(), subscriberArgumentCaptor.capture());
        subscriberArgumentCaptor.getValue().onNext(movieDetailsWrapper);

        verify(mockMovieDetailsView).showMovieDetails(movieDetailsWrapper);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openPersonDetails(person);

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openMovieDetails(movie);

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldShowToolbarTitle_whenScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = true;

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).showToolbarTitle();

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldHideToolbarTitle_whenNotScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = false;

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).hideToolbarTitle();

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onDestroyView_shouldClearSubscriptions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockMovieDetailsView);
        verify(mockMovieDetailsUseCase).clearSubscriptions();
    }

    // endregion
}
