package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.MovieCreditDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.MovieDetailsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresentationContract;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresenter;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

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
    private MovieDetailsPresentationContract.View mockMovieDetailsView;
    @Mock
    private MovieDetailsDomainContract.UseCase mockMovieDetailsUseCase;

    // Stubs
    // endregion

    // region Member Variables
    private MovieDetailsPresenter movieDetailsPresenter;
    private MovieDetailsPresentationModelMapper movieDetailsPresentationModelMapper = new MovieDetailsPresentationModelMapper();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        movieDetailsPresenter = new MovieDetailsPresenter(mockMovieDetailsView, mockMovieDetailsUseCase, new TestSchedulerProvider());
    }

    // region Test Methods
    @Test(expected = UnknownHostException.class)
    public void onLoadMovieDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        MovieDomainModel movie = new MovieDomainModel();
        movie.setId(1);

        when(mockMovieDetailsUseCase.getMovieDetails(anyInt())).thenThrow(UnknownHostException.class);

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movie.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).showErrorView();
    }

    @Test
    public void onLoadMovieDetails_shouldShowMovieDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        MovieDomainModel movie = new MovieDomainModel();
//        movie.setId(346364);
        movie.setId(1);
        List<MovieCreditDomainModel> cast = new ArrayList<>();
        List<MovieCreditDomainModel> crew = new ArrayList<>();
        List<MovieDomainModel> similarMovies = new ArrayList<>();
        String rating = "";
        MovieDetailsDomainModel movieDetailsDomainModel = new MovieDetailsDomainModel();
        movieDetailsDomainModel.setRating(rating);
        movieDetailsDomainModel.setCast(cast);
        movieDetailsDomainModel.setCrew(crew);
        movieDetailsDomainModel.setMovie(movie);
        movieDetailsDomainModel.setSimilarMovies(similarMovies);

        when(mockMovieDetailsUseCase.getMovieDetails(anyInt())).thenReturn(Single.just(movieDetailsDomainModel));
//        when(mockMovieDetailsUseCase.getMovieDetails(anyInt())).thenReturn(refEq(Single.just(movieDetailsDomainModel)));

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onLoadMovieDetails(movie.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).showMovieDetails(movieDetailsPresentationModelMapper.mapToPresentationModel(movieDetailsDomainModel));
//        verify(mockMovieDetailsView).showMovieDetails(refEq(movieDetailsPresentationModelMapper.mapToPresentationModel(movieDetailsDomainModel)));
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonPresentationModel person = new PersonPresentationModel();

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockMovieDetailsView).openPersonDetails(person);

        verifyZeroInteractions(mockMovieDetailsUseCase);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        MoviePresentationModel movie = new MoviePresentationModel();

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
    public void onDestroyView_shouldNotInteractWithViewOrUsecase() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        movieDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockMovieDetailsView);
        verifyZeroInteractions(mockMovieDetailsUseCase);
    }



    // endregion
}
