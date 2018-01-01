package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.domain.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsUiContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private PersonDetailsUiContract.View mockPersonDetailsView;
    @Mock
    private PersonDetailsDomainContract.UseCase mockPersonDetailsUseCase;

    // Stubs
    private ArgumentCaptor<DisposableSingleObserver> disposableSingleObserverArgumentCaptor;

    // endregion

    // region Member Variables
    private PersonDetailsDomainModel personDetailsDomainModel;
    private PersonDetailsPresenter personDetailsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        personDetailsPresenter = new PersonDetailsPresenter(mockPersonDetailsView, mockPersonDetailsUseCase);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadTelevisionShowDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();
        person.setId(1);
        List<PersonCreditResponse> cast = new ArrayList<>();
        List<PersonCreditResponse> crew = new ArrayList<>();
        personDetailsDomainModel = new PersonDetailsDomainModel(person, cast, crew);

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onLoadPersonDetails(person.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockPersonDetailsUseCase).getPersonDetails(anyInt(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onError(new UnknownHostException());

        verify(mockPersonDetailsView).showErrorView();
    }

    @Test
    public void onLoadTelevisionShowDetails_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();
        person.setId(1);
        List<PersonCreditResponse> cast = new ArrayList<>();
        List<PersonCreditResponse> crew = new ArrayList<>();
        personDetailsDomainModel = new PersonDetailsDomainModel(person, cast, crew);

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onLoadPersonDetails(person.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockPersonDetailsUseCase).getPersonDetails(anyInt(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onSuccess(personDetailsDomainModel);

        verify(mockPersonDetailsView).showPersonDetails(personDetailsDomainModel);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).openMovieDetails(movie);

        verifyZeroInteractions(mockPersonDetailsUseCase);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockPersonDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldShowToolbarTitle_whenScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = true;

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).showToolbarTitle();

        verifyZeroInteractions(mockPersonDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldHideToolbarTitle_whenNotScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = false;

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).hideToolbarTitle();

        verifyZeroInteractions(mockPersonDetailsUseCase);
    }

    @Test
    public void onDestroyView_shouldClearSubscriptions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockPersonDetailsView);
        verify(mockPersonDetailsUseCase).clearDisposables();
    }

    // endregion
}
