package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.SearchDomainContract;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.search.SearchPresentationContract;
import com.etiennelawlor.moviehub.presentation.search.SearchPresenter;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class SearchPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private SearchPresentationContract.View mockSearchView;
    @Mock
    private SearchDomainContract.UseCase mockSearchUseCase;

    // Stubs
//    private ArgumentCaptor<DisposableSingleObserver> disposableSingleObserverArgumentCaptor;
    private SearchDomainModel searchDomainModelStub;
    // endregion

    // region Member Variables
    private SearchPresenter searchPresenter;
    private SchedulerProvider schedulerProvider = new TestSchedulerProvider();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        searchPresenter = new SearchPresenter(mockSearchView, mockSearchUseCase, schedulerProvider);
    }

    // region Test Methods

    //@Test(expected = IOException.class)
    @Test
    public void onLoadSearch_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        searchDomainModelStub = getSearchDomainModelStub();

        CharSequence[] queries = {"J", "Je", "Jen", "Jenn", "Jenni", "Jennif", "Jennife", "Jennifer"};
        Observable<CharSequence> searchQueriesObservable = Observable.fromArray(queries);

        when(mockSearchUseCase.getSearchResponse(anyString())).thenReturn(Single.error(new IOException()));


        // 2. (When) Then perform one or more actions
        searchPresenter.onLoadSearch(searchQueriesObservable);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView, times(queries.length+1)).hideLoadingView();

        verify(mockSearchView).showErrorView();
    }

    // Test fails because of scheudluers
    @Test
    public void onLoadSearch_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        searchDomainModelStub = getSearchDomainModelStub();

        CharSequence[] queries = {"J", "Je", "Jen", "Jenn", "Jenni", "Jennif", "Jennife", "Jennifer"};
        Observable<CharSequence> searchQueriesObservable = Observable.fromArray(queries);

        when(mockSearchUseCase.getSearchResponse(anyString())).thenReturn(Single.just(searchDomainModelStub));

        // 2. (When) Then perform one or more actions
        searchPresenter.onLoadSearch(searchQueriesObservable);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView, times(queries.length+1)).hideLoadingView();

        verify(mockSearchView).clearMoviesAdapter();
        verify(mockSearchView).clearTelevisionShowsAdapter();
        verify(mockSearchView).clearPersonsAdapter();
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        MoviePresentationModel movie = new MoviePresentationModel();

        // 2. (When) Then perform one or more actions
        searchPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openMovieDetails(movie);

        verifyZeroInteractions(mockSearchUseCase);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowPresentationModel televisionShow = new TelevisionShowPresentationModel();

        // 2. (When) Then perform one or more actions
        searchPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockSearchUseCase);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonPresentationModel person = new PersonPresentationModel();

        // 2. (When) Then perform one or more actions
        searchPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openPersonDetails(person);

        verifyZeroInteractions(mockSearchUseCase);
    }

    @Test
    public void onDestroyView_shouldNotHaveInteractions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        searchPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockSearchView);
        verifyZeroInteractions(mockSearchUseCase);
    }

    // endregion

    // region Helper Methods
    private List<MovieDomainModel> getListOfMovies(int numOfMovies){
        List<MovieDomainModel> movies = new ArrayList<>();
        for(int i=0; i<numOfMovies; i++){
            MovieDomainModel movie = new MovieDomainModel();
            movies.add(movie);
        }
        return movies;
    }

    private List<TelevisionShowDomainModel> getListOfTelevisionShows(int numOfTelevisionShows){
        List<TelevisionShowDomainModel> televisionShows = new ArrayList<>();
        for(int i=0; i<numOfTelevisionShows; i++){
            TelevisionShowDomainModel televisionShow = new TelevisionShowDomainModel();
            televisionShows.add(televisionShow);
        }
        return televisionShows;
    }

    private List<PersonDomainModel> getListOfPersons(int numOfPersons){
        List<PersonDomainModel> persons = new ArrayList<>();
        for(int i=0; i<numOfPersons; i++){
            PersonDomainModel person = new PersonDomainModel();
            persons.add(person);
        }
        return persons;
    }

    private SearchDomainModel getSearchDomainModelStub(){
        SearchDomainModel searchDomainModel = new SearchDomainModel();
        String query = "";
        List<MovieDomainModel> movies = getListOfMovies(0);
        List<TelevisionShowDomainModel> televisionShows = getListOfTelevisionShows(0);
        List<PersonDomainModel> persons = getListOfPersons(0);
        searchDomainModel.setQuery(query);
        searchDomainModel.setMovies(movies);
        searchDomainModel.setTelevisionShows(televisionShows);
        searchDomainModel.setPersons(persons);
        return searchDomainModel;
    }
    // endregion

}
