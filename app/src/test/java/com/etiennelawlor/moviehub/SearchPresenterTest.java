package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.SearchDomainContract;
import com.etiennelawlor.moviehub.presentation.search.SearchPresenter;
import com.etiennelawlor.moviehub.presentation.search.SearchUiContract;
import com.etiennelawlor.moviehub.util.rxjava.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class SearchPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private SearchUiContract.View mockSearchView;
    @Mock
    private SearchDomainContract.UseCase mockSearchUseCase;

    // Stubs
    private ArgumentCaptor<DisposableSingleObserver> disposableSingleObserverArgumentCaptor;
    // endregion

    // region Member Variables
    private SearchDomainModel searchDomainModel;
    private SearchPresenter searchPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        searchPresenter = new SearchPresenter(mockSearchView, mockSearchUseCase, new TestSchedulerProvider());
    }

    // region Test Methods

    // Test fails because of scheudluers
    //    @Test(expected = IOException.class)
    @Test
    public void onLoadSearch_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        String query = "";
        List<MovieResponse> movies = getListOfMovies(0);
        List<TelevisionShowResponse> televisionShows = getListOfTelevisionShows(0);
        List<PersonResponse> persons = getListOfPersons(0);
        searchDomainModel = new SearchDomainModel(query, movies, televisionShows, persons);

        CharSequence[] queries = {"J", "Je", "Jen", "Jenn", "Jenni", "Jennif", "Jennife", "Jennifer"};
        Observable<CharSequence> searchQueriesObservable = Observable.fromArray(queries);

        // 2. (When) Then perform one or more actions
        searchPresenter.onLoadSearch(searchQueriesObservable);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView, times(queries.length)).hideLoadingView();

        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockSearchUseCase).getSearchResponse(anyString(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onError(new UnknownHostException());

        verify(mockSearchView).showErrorView();
    }

    // Test fails because of scheudluers
    @Test
    public void onLoadSearch_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        String query = "";
        List<MovieResponse> movies = getListOfMovies(0);
        List<TelevisionShowResponse> televisionShows = getListOfTelevisionShows(0);
        List<PersonResponse> persons = getListOfPersons(0);
        searchDomainModel = new SearchDomainModel(query, movies, televisionShows, persons);

        CharSequence[] queries = {"J", "Je", "Jen", "Jenn", "Jenni", "Jennif", "Jennife", "Jennifer"};
        Observable<CharSequence> searchQueriesObservable = Observable.fromArray(queries);

        // 2. (When) Then perform one or more actions
        searchPresenter.onLoadSearch(searchQueriesObservable);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView, times(queries.length)).hideLoadingView();

        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockSearchUseCase).getSearchResponse(anyString(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onSuccess(searchDomainModel);

        verify(mockSearchView).clearMoviesAdapter();
        verify(mockSearchView).clearTelevisionShowsAdapter();
        verify(mockSearchView).clearPersonsAdapter();
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        MovieResponse movie = new MovieResponse();

        // 2. (When) Then perform one or more actions
        searchPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openMovieDetails(movie);

        verifyZeroInteractions(mockSearchUseCase);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowResponse televisionShow = new TelevisionShowResponse();

        // 2. (When) Then perform one or more actions
        searchPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockSearchUseCase);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonResponse person = new PersonResponse();

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
    private List<MovieResponse> getListOfMovies(int numOfMovies){
        List<MovieResponse> movies = new ArrayList<>();
        for(int i=0; i<numOfMovies; i++){
            MovieResponse movie = new MovieResponse();
            movies.add(movie);
        }
        return movies;
    }

    private List<TelevisionShowResponse> getListOfTelevisionShows(int numOfTelevisionShows){
        List<TelevisionShowResponse> televisionShows = new ArrayList<>();
        for(int i=0; i<numOfTelevisionShows; i++){
            TelevisionShowResponse televisionShow = new TelevisionShowResponse();
            televisionShows.add(televisionShow);
        }
        return televisionShows;
    }

    private List<PersonResponse> getListOfPersons(int numOfPersons){
        List<PersonResponse> persons = new ArrayList<>();
        for(int i=0; i<numOfPersons; i++){
            PersonResponse person = new PersonResponse();
            persons.add(person);
        }
        return persons;
    }
    // endregion

}
