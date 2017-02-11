package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.repository.MoviesRepository;
import com.etiennelawlor.moviehub.ui.movies.MoviesContract;
import com.etiennelawlor.moviehub.ui.movies.MoviesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenterTest {

    // region Member Variables
    @Mock
    private MoviesRepository moviesRepository;
    @Mock
    private MoviesContract.View moviesView;
    private MoviesPresenter moviesPresenter;
    // endregion


    @Before
    public void setupNotesPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        moviesPresenter = new MoviesPresenter(moviesRepository, moviesView);

    }

    // Test method names
    // methodName_codepathExplanation
    // methodName_StateUnderTest_ExpectedBehavior
    // methodName_InputValues_ExpectedBehavior
    // methodName_WithCertainState_ShouldDoSomething
    // methodName_WhenX_ExpectY

    @Test
    public void loadMovies_whenX_expectY(){

//        moviesPresenter.loadMovies();
//        verify(moviesView)

    }
}
