package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.model.SearchWrapper;
import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowCredit;
import com.etiennelawlor.moviehub.data.source.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.data.source.televisionshowdetails.TelevisionShowDetailsDataSourceContract;
import com.etiennelawlor.moviehub.ui.search.SearchPresenter;
import com.etiennelawlor.moviehub.ui.search.SearchUiContract;
import com.etiennelawlor.moviehub.ui.televisionshowdetails.TelevisionShowDetailsPresenter;
import com.etiennelawlor.moviehub.ui.televisionshowdetails.TelevisionShowDetailsUiContract;
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
    private SearchUiContract.View mockSearchView;
    @Mock
    private SearchDataSourceContract.Repository mockSearchRepository;

    // Stubs
    private Observable stub;

    // endregion

    // region Member Variables
    private SearchWrapper searchWrapper;
    private SearchPresenter searchPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        searchPresenter = new SearchPresenter(mockSearchView, mockSearchRepository, new TestSchedulerTransformer<SearchWrapper>());
    }

    // region Test Methods

    // Test fails because of scheudluers
    //    @Test(expected = IOException.class)
    @Test
    public void onLoadSearch_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        String query = "";
        List<Movie> movies = getListOfMovies(0);
        List<TelevisionShow> televisionShows = getListOfTelevisionShows(0);
        List<Person> persons = getListOfPersons(0);
        searchWrapper = new SearchWrapper(query, movies, televisionShows, persons);
        stub = Observable.<SearchWrapper>error(new IOException());
        when(mockSearchRepository.getSearch(anyString())).thenReturn(stub);

        CharSequence[] queries = {"J", "Je", "Jen", "Jenn", "Jenni", "Jennif", "Jennife", "Jennifer"};
        Observable<CharSequence> searchQueryChangeObservable = Observable.from(queries);

        // 2. (When) Then perform one or more actions
        searchPresenter.onLoadSearch(searchQueryChangeObservable);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView, times(queries.length+1)).hideLoadingView();

        verify(mockSearchView).showErrorView();
    }

    // Test fails because of scheudluers
    @Test
    public void onLoadSearch_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        String query = "";
        List<Movie> movies = getListOfMovies(0);
        List<TelevisionShow> televisionShows = getListOfTelevisionShows(0);
        List<Person> persons = getListOfPersons(0);
        searchWrapper = new SearchWrapper(query, movies, televisionShows, persons);
        stub = Observable.just(searchWrapper);
        when(mockSearchRepository.getSearch(anyString())).thenReturn(stub);

        CharSequence[] queries = {"J", "Je", "Jen", "Jenn", "Jenni", "Jennif", "Jennife", "Jennifer"};
        Observable<CharSequence> searchQueryChangeObservable = Observable.from(queries);

        // 2. (When) Then perform one or more actions
        searchPresenter.onLoadSearch(searchQueryChangeObservable);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView, times(queries.length+1)).hideLoadingView();

        verify(mockSearchView).clearMoviesAdapter();
        verify(mockSearchView).clearTelevisionShowsAdapter();
        verify(mockSearchView).clearPersonsAdapter();
    }

    @Test
    public void onMovieClick_shouldOpenMovieDetails() {
        // 1. (Given) Set up conditions required for the test
        Movie movie = new Movie();

        // 2. (When) Then perform one or more actions
        searchPresenter.onMovieClick(movie);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openMovieDetails(movie);

        verifyZeroInteractions(mockSearchRepository);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();

        // 2. (When) Then perform one or more actions
        searchPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockSearchRepository);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();

        // 2. (When) Then perform one or more actions
        searchPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockSearchView).openPersonDetails(person);

        verifyZeroInteractions(mockSearchRepository);
    }

    @Test
    public void onDestroyView_shouldNotHaveInteractions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        searchPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockSearchView);
        verifyZeroInteractions(mockSearchRepository);
    }

    // endregion

    // region Helper Methods
    private List<Movie> getListOfMovies(int numOfMovies){
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<numOfMovies; i++){
            Movie movie = new Movie();
            movies.add(movie);
        }
        return movies;
    }

    private List<TelevisionShow> getListOfTelevisionShows(int numOfTelevisionShows){
        List<TelevisionShow> televisionShows = new ArrayList<>();
        for(int i=0; i<numOfTelevisionShows; i++){
            TelevisionShow televisionShow = new TelevisionShow();
            televisionShows.add(televisionShow);
        }
        return televisionShows;
    }

    private List<Person> getListOfPersons(int numOfPersons){
        List<Person> persons = new ArrayList<>();
        for(int i=0; i<numOfPersons; i++){
            Person person = new Person();
            persons.add(person);
        }
        return persons;
    }
    // endregion

}
