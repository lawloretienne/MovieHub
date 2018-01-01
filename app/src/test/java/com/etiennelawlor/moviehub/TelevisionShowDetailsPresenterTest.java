package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.domain.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsUiContract;

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

public class TelevisionShowDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private TelevisionShowDetailsUiContract.View mockTelevisionShowDetailsView;
    @Mock
    private TelevisionShowDetailsDomainContract.UseCase mockTelevisionShowDetailsUseCase;

    // Stubs
    private ArgumentCaptor<DisposableSingleObserver> disposableSingleObserverArgumentCaptor;
    // endregion

    // region Member Variables
    private TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel;
    private TelevisionShowDetailsPresenter televisionShowDetailsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        televisionShowDetailsPresenter = new TelevisionShowDetailsPresenter(mockTelevisionShowDetailsView, mockTelevisionShowDetailsUseCase);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadTelevisionShowDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowResponse televisionShow = new TelevisionShowResponse();
        televisionShow.setId(1);
        List<TelevisionShowCreditResponse> cast = new ArrayList<>();
        List<TelevisionShowCreditResponse> crew = new ArrayList<>();
        List<TelevisionShowResponse> similarTelevisionShows = new ArrayList<>();
        String rating = "";
        televisionShowDetailsDomainModel = new TelevisionShowDetailsDomainModel(televisionShow, cast, crew, similarTelevisionShows, rating);

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onLoadTelevisionShowDetails(televisionShow.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockTelevisionShowDetailsUseCase).getTelevisionShowDetails(anyInt(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onError(new UnknownHostException());

        verify(mockTelevisionShowDetailsView).showErrorView();
    }

    @Test
    public void onLoadTelevisionShowDetails_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowResponse televisionShow = new TelevisionShowResponse();
        televisionShow.setId(1);
        List<TelevisionShowCreditResponse> cast = new ArrayList<>();
        List<TelevisionShowCreditResponse> crew = new ArrayList<>();
        List<TelevisionShowResponse> similarTelevisionShows = new ArrayList<>();
        String rating = "";
        televisionShowDetailsDomainModel = new TelevisionShowDetailsDomainModel(televisionShow, cast, crew, similarTelevisionShows, rating);

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onLoadTelevisionShowDetails(televisionShow.getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        disposableSingleObserverArgumentCaptor = ArgumentCaptor.forClass(DisposableSingleObserver.class);
        verify(mockTelevisionShowDetailsUseCase).getTelevisionShowDetails(anyInt(), disposableSingleObserverArgumentCaptor.capture());
        disposableSingleObserverArgumentCaptor.getValue().onSuccess(televisionShowDetailsDomainModel);

        verify(mockTelevisionShowDetailsView).showTelevisionShowDetails(televisionShowDetailsDomainModel);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonResponse person = new PersonResponse();

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).openPersonDetails(person);

        verifyZeroInteractions(mockTelevisionShowDetailsUseCase);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowResponse televisionShow = new TelevisionShowResponse();

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockTelevisionShowDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldShowToolbarTitle_whenScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = true;

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).showToolbarTitle();

        verifyZeroInteractions(mockTelevisionShowDetailsUseCase);
    }

    @Test
    public void onScrollChange_shouldHideToolbarTitle_whenNotScrolledPastThreshold() {
        // 1. (Given) Set up conditions required for the test
        boolean isScrolledPastThreshold = false;

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onScrollChange(isScrolledPastThreshold);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).hideToolbarTitle();

        verifyZeroInteractions(mockTelevisionShowDetailsUseCase);
    }

    @Test
    public void onDestroyView_shouldClearSubscriptions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockTelevisionShowDetailsView);
        verify(mockTelevisionShowDetailsUseCase).clearDisposables();
    }

    // endregion
}
