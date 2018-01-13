package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.MovieCreditDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresentationContract;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresenter;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
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
    private MovieDetailsPresentationContract.View mockMovieDetailsView;
    @Mock
    private MovieDetailsDomainContract.UseCase mockMovieDetailsUseCase;

    // Stubs
    private MovieDetailsDomainModel movieDetailsDomainModelStub;
    // endregion

    // region Member Variables
    private MovieDetailsPresenter movieDetailsPresenter;
    private SchedulerProvider schedulerProvider = new TestSchedulerProvider();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        movieDetailsPresenter = new MovieDetailsPresenter(mockMovieDetailsView, mockMovieDetailsUseCase, schedulerProvider);
    }

    // region Test Methods
    @Test
    public void onLoadMovieDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        movieDetailsDomainModelStub = getMovieDetailsDomainModelStub();

        when(mockMovieDetailsUseCase.getMovieDetails(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movieDetailsDomainModelStub.getMovie().getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).showErrorView();
        verify(mockMovieDetailsView, never()).showMovieDetails(movieDetailsDomainModelStub);

    }

    @Test
    public void onLoadMovieDetails_shouldShowMovieDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        movieDetailsDomainModelStub = getMovieDetailsDomainModelStub();

        when(mockMovieDetailsUseCase.getMovieDetails(anyInt())).thenReturn(Single.just(movieDetailsDomainModelStub));


        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movieDetailsDomainModelStub.getMovie().getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).showMovieDetails(movieDetailsDomainModelStub);
        verify(mockMovieDetailsView, never()).showErrorView();
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonPresentationModel personStub = new PersonPresentationModel();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onPersonClick(personStub);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openPersonDetails(personStub);

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        MoviePresentationModel movieStub = new MoviePresentationModel();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onMovieClick(movieStub);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openMovieDetails(movieStub);

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
    public void onDestroyView_shouldNotInteractWithViewOrUsecase() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockMovieDetailsView);
        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    // endregion

    // region Helper Methods
    private MovieDetailsDomainModel getMovieDetailsDomainModelStub(){
        MovieDetailsDomainModel movieDetailsDomainModel = new MovieDetailsDomainModel();
        MovieDomainModel movie = new MovieDomainModel();
        movie.setId(1);
        List<MovieCreditDomainModel> cast = new ArrayList<>();
        List<MovieCreditDomainModel> crew = new ArrayList<>();
        List<MovieDomainModel> similarMovies = new ArrayList<>();
        String rating = "";
        movieDetailsDomainModel.setRating(rating);
        movieDetailsDomainModel.setCast(cast);
        movieDetailsDomainModel.setCrew(crew);
        movieDetailsDomainModel.setMovie(movie);
        movieDetailsDomainModel.setSimilarMovies(similarMovies);
        return movieDetailsDomainModel;
    }
    // endregion
}
