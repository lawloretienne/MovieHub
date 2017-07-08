package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.source.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCredit;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.source.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsUiContract;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private PersonDetailsUiContract.View mockPersonDetailsView;
    @Mock
    private PersonDataSourceContract.Repository mockPersonRepository;

    // Stubs
    private Observable stub;

    // endregion

    // region Member Variables
    private PersonDetailsWrapper personDetailsWrapper;
    private PersonDetailsPresenter personDetailsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        personDetailsPresenter = new PersonDetailsPresenter(mockPersonDetailsView, mockPersonRepository, new TestSchedulerTransformer<PersonDetailsWrapper>());
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadTelevisionShowDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();
        person.setId(1);
        List<PersonCredit> cast = new ArrayList<>();
        List<PersonCredit> crew = new ArrayList<>();
        personDetailsWrapper = new PersonDetailsWrapper(person, cast, crew);
        stub = Observable.<PersonDetailsWrapper>error(new IOException());
        when(mockPersonRepository.getPersonDetails(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onLoadPersonDetails(person.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).showErrorView();
    }

    @Test
    public void onLoadTelevisionShowDetails_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();
        person.setId(1);
        List<PersonCredit> cast = new ArrayList<>();
        List<PersonCredit> crew = new ArrayList<>();
        personDetailsWrapper = new PersonDetailsWrapper(person, cast, crew);
        stub = Observable.just(personDetailsWrapper);
        when(mockPersonRepository.getPersonDetails(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onLoadPersonDetails(person.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).showPersonDetails(personDetailsWrapper);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).openMovieDetails(movie);

        verifyZeroInteractions(mockPersonRepository);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockPersonRepository);
    }

    @Test
    public void onScrollChange_shouldShowToolbarTitle_whenScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = true;

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).showToolbarTitle();

        verifyZeroInteractions(mockPersonRepository);
    }

    @Test
    public void onScrollChange_shouldHideToolbarTitle_whenNotScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = false;

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).hideToolbarTitle();

        verifyZeroInteractions(mockPersonRepository);
    }

    @Test
    public void onDestroyView_shouldNotHaveInteractions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockPersonDetailsView);
        verifyZeroInteractions(mockPersonRepository);
    }

    // endregion
}
