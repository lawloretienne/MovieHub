package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.PersonsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresentationContract;
import com.etiennelawlor.moviehub.presentation.persons.PersonsPresenter;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private PersonsPresentationContract.View mockPersonsView;
    @Mock
    private PersonsDomainContract.UseCase mockPersonsUseCase;

    // Stubs
    private PersonsDomainModel personsDomainModelStub;
    // endregion

    // region Member Variables
    private PersonsPresenter personsPresenter;
    private SchedulerProvider schedulerProvider = new TestSchedulerProvider();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        personsPresenter = new PersonsPresenter(mockPersonsView, mockPersonsUseCase, schedulerProvider);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadPopularPersons_shouldShowError_whenFirstPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(0, 1, true);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).hideEmptyView();
        verify(mockPersonsView).hideErrorView();
        verify(mockPersonsView).showLoadingView();

        verify(mockPersonsView).hideLoadingView();
        verify(mockPersonsView).setErrorText(anyString());
        verify(mockPersonsView).showErrorView();
    }

    @Test
    public void onLoadPopularPersons_shouldShowError_whenNextPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(0, 2, true);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).showLoadingFooter();

        verify(mockPersonsView).showErrorFooter();
    }

    @Test
    public void onLoadPopularPersons_shouldShowEmpty_whenFirstPageHasNoPersons() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(0, 1, true);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.just(personsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).hideEmptyView();
        verify(mockPersonsView).hideErrorView();
        verify(mockPersonsView).showLoadingView();

        verify(mockPersonsView).hideLoadingView();
        verify(mockPersonsView).showEmptyView();
        verify(mockPersonsView).setPersonsDomainModel(personsDomainModelStub);
    }

    @Test
    public void onLoadPopularPersons_shouldNotAddPersons_whenNextPageHasNoPersons() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(0, 2, true);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.just(personsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).showLoadingFooter();

        verify(mockPersonsView).removeFooter();
        verify(mockPersonsView).setPersonsDomainModel(personsDomainModelStub);
    }

    @Test
    public void onLoadPopularPersons_shouldAddPersons_whenFirstPageHasPersonsAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(5, 1, true);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.just(personsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).hideEmptyView();
        verify(mockPersonsView).hideErrorView();
        verify(mockPersonsView).showLoadingView();

        verify(mockPersonsView).hideLoadingView();
        verify(mockPersonsView).addHeader();
        verify(mockPersonsView).addPersonsToAdapter(personsDomainModelStub.getPersons());
        verify(mockPersonsView).setPersonsDomainModel(personsDomainModelStub);
    }

    @Test
    public void onLoadPopularPersons_shouldAddPersons_whenFirstPageHasPersonsAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(5, 1, false);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.just(personsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).hideEmptyView();
        verify(mockPersonsView).hideErrorView();
        verify(mockPersonsView).showLoadingView();

        verify(mockPersonsView).hideLoadingView();
        verify(mockPersonsView).addHeader();
        verify(mockPersonsView).addPersonsToAdapter(personsDomainModelStub.getPersons());
        verify(mockPersonsView).addFooter();
        verify(mockPersonsView).setPersonsDomainModel(personsDomainModelStub);
    }

    @Test
    public void onLoadPopularPersons_shouldAddPersons_whenNextPageHasPersonsAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(5, 2, true);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.just(personsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).showLoadingFooter();

        verify(mockPersonsView).removeFooter();
        verify(mockPersonsView).addPersonsToAdapter(personsDomainModelStub.getPersons());
        verify(mockPersonsView).setPersonsDomainModel(personsDomainModelStub);
    }

    @Test
    public void onLoadPopularPersons_shouldAddPersons_whenNextPageHasPersonsAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        personsDomainModelStub = getPersonsDomainModelStub(5, 2, false);

        when(mockPersonsUseCase.getPopularPersons(anyInt())).thenReturn(Single.just(personsDomainModelStub));

        // 2. (When) Then perform one or more actions
        personsPresenter.onLoadPopularPersons(personsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).showLoadingFooter();

        verify(mockPersonsView).removeFooter();
        verify(mockPersonsView).addPersonsToAdapter(personsDomainModelStub.getPersons());
        verify(mockPersonsView).addFooter();
        verify(mockPersonsView).setPersonsDomainModel(personsDomainModelStub);
//        verify(mockPersonsView, times(1)).setModel(any(PersonsWrapper.class)); // Alternative verify check
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonPresentationModel person = new PersonPresentationModel();

        // 2. (When) Then perform one or more actions
        personsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).openPersonDetails(person);

        verifyZeroInteractions(mockPersonsUseCase);
    }

    @Test
    public void onScrollToEndOfList_shouldLoadMoreItems() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        personsPresenter.onScrollToEndOfList();

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockPersonsView).loadMoreItems();

        verifyZeroInteractions(mockPersonsUseCase);
    }

    @Test
    public void onDestroyView_shouldNotInteractWithViewOrUsecase() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        personsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockPersonsView);
        verifyZeroInteractions(mockPersonsUseCase);
    }

    // endregion

    // region Helper Methods
    private List<PersonDomainModel> getListOfPersons(int numOfPersons){
        List<PersonDomainModel> persons = new ArrayList<>();
        for(int i=0; i<numOfPersons; i++){
            PersonDomainModel person = new PersonDomainModel();
            persons.add(person);
        }
        return persons;
    }

    private PersonsDomainModel getPersonsDomainModelStub(int numOfPersons, int pageNumber, boolean lastPage){
        PersonsDomainModel personsDomainModel = new PersonsDomainModel();
        personsDomainModel.setPersons(getListOfPersons(numOfPersons));
        personsDomainModel.setPageNumber(pageNumber);
        personsDomainModel.setLastPage(lastPage);
        personsDomainModel.setExpiredAt(Calendar.getInstance().getTime());
        return personsDomainModel;
    }
    // endregion
}
