package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCredit;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsUiContract;
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

public class TelevisionShowDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private TelevisionShowDetailsUiContract.View mockTelevisionShowDetailsView;
    @Mock
    private TelevisionShowDataSourceContract.Repository mockTelevisionShowRepository;

    // Stubs
    private Observable stub;

    // endregion

    // region Member Variables
    private TelevisionShowDetailsWrapper televisionShowDetailsWrapper;
    private TelevisionShowDetailsPresenter televisionShowDetailsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        televisionShowDetailsPresenter = new TelevisionShowDetailsPresenter(mockTelevisionShowDetailsView, mockTelevisionShowRepository, new TestSchedulerTransformer<TelevisionShowDetailsWrapper>());
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadTelevisionShowDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();
        televisionShow.setId(1);
        List<TelevisionShowCredit> cast = new ArrayList<>();
        List<TelevisionShowCredit> crew = new ArrayList<>();
        List<TelevisionShow> similarTelevisionShows = new ArrayList<>();
        String rating = "";
        televisionShowDetailsWrapper = new TelevisionShowDetailsWrapper(televisionShow, cast, crew, similarTelevisionShows, rating);
        stub = Observable.<TelevisionShowDetailsWrapper>error(new IOException());
        when(mockTelevisionShowRepository.getTelevisionShowDetails(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onLoadTelevisionShowDetails(televisionShow.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).showErrorView();
    }

    @Test
    public void onLoadTelevisionShowDetails_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();
        televisionShow.setId(1);
        List<TelevisionShowCredit> cast = new ArrayList<>();
        List<TelevisionShowCredit> crew = new ArrayList<>();
        List<TelevisionShow> similarTelevisionShows = new ArrayList<>();
        String rating = "";
        televisionShowDetailsWrapper = new TelevisionShowDetailsWrapper(televisionShow, cast, crew, similarTelevisionShows, rating);
        stub = Observable.just(televisionShowDetailsWrapper);
        when(mockTelevisionShowRepository.getTelevisionShowDetails(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onLoadTelevisionShowDetails(televisionShow.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).showTelevisionShowDetails(televisionShowDetailsWrapper);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        Person person = new Person();

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).openPersonDetails(person);

        verifyZeroInteractions(mockTelevisionShowRepository);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockTelevisionShowRepository);
    }

    @Test
    public void onScrollChange_shouldShowToolbarTitle_whenScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = true;

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).showToolbarTitle();

        verifyZeroInteractions(mockTelevisionShowRepository);
    }

    @Test
    public void onScrollChange_shouldHideToolbarTitle_whenNotScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = false;

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).hideToolbarTitle();

        verifyZeroInteractions(mockTelevisionShowRepository);
    }

    @Test
    public void onDestroyView_shouldNotHaveInteractions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockTelevisionShowDetailsView);
        verifyZeroInteractions(mockTelevisionShowRepository);
    }

    // endregion
}
