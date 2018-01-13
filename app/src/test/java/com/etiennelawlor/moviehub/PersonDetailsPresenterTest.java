package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.PersonCreditDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresentationContract;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsPresenter;
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
    private PersonDetailsPresentationContract.View mockPersonDetailsView;
    @Mock
    private PersonDetailsDomainContract.UseCase mockPersonDetailsUseCase;

    // Stubs
    private PersonDetailsDomainModel personDetailsDomainModelStub;
    // endregion

    // region Member Variables
    private PersonDetailsPresenter personDetailsPresenter;
    private SchedulerProvider schedulerProvider = new TestSchedulerProvider();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        personDetailsPresenter = new PersonDetailsPresenter(mockPersonDetailsView, mockPersonDetailsUseCase, schedulerProvider);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadTelevisionShowDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        personDetailsDomainModelStub = getPersonDetailsDomainModelStub();

        when(mockPersonDetailsUseCase.getPersonDetails(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onLoadPersonDetails(personDetailsDomainModelStub.getPerson().getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).showErrorView();
    }

    @Test
    public void onLoadTelevisionShowDetails_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        personDetailsDomainModelStub = getPersonDetailsDomainModelStub();

        when(mockPersonDetailsUseCase.getPersonDetails(anyInt())).thenReturn(Single.just(personDetailsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onLoadPersonDetails(personDetailsDomainModelStub.getPerson().getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).showPersonDetails(personDetailsDomainModelStub);
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        MoviePresentationModel movie = new MoviePresentationModel();

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonDetailsView).openMovieDetails(movie);

        verifyZeroInteractions(mockPersonDetailsUseCase);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowPresentationModel televisionShow = new TelevisionShowPresentationModel();

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
    public void onDestroyView_shouldNotInteractWithViewOrUsecase() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        personDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockPersonDetailsView);
        verifyZeroInteractions(mockPersonDetailsUseCase);
    }

    // endregion

    // region Helper Methods
    private PersonDetailsDomainModel getPersonDetailsDomainModelStub(){
        PersonDetailsDomainModel personDetailsDomainModel = new PersonDetailsDomainModel();
        PersonDomainModel person = new PersonDomainModel();
        person.setId(1);
        List<PersonCreditDomainModel> cast = new ArrayList<>();
        List<PersonCreditDomainModel> crew = new ArrayList<>();
        personDetailsDomainModel.setPerson(person);
        personDetailsDomainModel.setCast(cast);
        personDetailsDomainModel.setCrew(crew);
        return personDetailsDomainModel;
    }
    // endregion
}
