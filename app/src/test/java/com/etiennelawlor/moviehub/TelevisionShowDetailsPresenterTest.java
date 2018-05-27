package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowCreditDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresentationContract;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresenter;
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

public class TelevisionShowDetailsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private TelevisionShowDetailsPresentationContract.View mockTelevisionShowDetailsView;
    @Mock
    private TelevisionShowDetailsDomainContract.UseCase mockTelevisionShowDetailsUseCase;

    // Stubs
    private TelevisionShowDetailsDomainModel televisionShowDetailsDomainModelStub;
    // endregion

    // region Member Variables
    private TelevisionShowDetailsPresenter televisionShowDetailsPresenter;
    private SchedulerProvider schedulerProvider = new TestSchedulerProvider();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        televisionShowDetailsPresenter = new TelevisionShowDetailsPresenter(mockTelevisionShowDetailsView, mockTelevisionShowDetailsUseCase, schedulerProvider);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadTelevisionShowDetails_shouldShowError_whenRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        televisionShowDetailsDomainModelStub = getTelevisionShowDetailsDomainModelStub();

        when(mockTelevisionShowDetailsUseCase.getTelevisionShowDetails(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onLoadTelevisionShowDetails(televisionShowDetailsDomainModelStub.getTelevisionShow().getId());

        verify(mockTelevisionShowDetailsView).showErrorView();
    }

    @Test
    public void onLoadTelevisionShowDetails_shouldShowTelevisionShowDetails_whenRequestSucceeded() {
        // 1. (Given) Set up conditions required for the test
        televisionShowDetailsDomainModelStub = getTelevisionShowDetailsDomainModelStub();

        when(mockTelevisionShowDetailsUseCase.getTelevisionShowDetails(anyInt())).thenReturn(Single.just(televisionShowDetailsDomainModelStub));


        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onLoadTelevisionShowDetails(televisionShowDetailsDomainModelStub.getTelevisionShow().getId());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).setTelevisionShowDetailsDomainModel(televisionShowDetailsDomainModelStub);
    }

    @Test
    public void onPersonClick_shouldOpenPersonDetails() {
        // 1. (Given) Set up conditions required for the test
        PersonPresentationModel person = new PersonPresentationModel();

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onPersonClick(person);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowDetailsView).openPersonDetails(person);

        verifyZeroInteractions(mockTelevisionShowDetailsUseCase);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowPresentationModel televisionShow = new TelevisionShowPresentationModel();

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
    public void onDestroyView_shouldNotInteractWithViewOrUsecase() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowDetailsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockTelevisionShowDetailsView);
        verifyZeroInteractions(mockTelevisionShowDetailsUseCase);
    }

    // endregion

    // region Helper Methods
    private TelevisionShowDetailsDomainModel getTelevisionShowDetailsDomainModelStub(){
        TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel = new TelevisionShowDetailsDomainModel();
        TelevisionShowDomainModel televisionShow = new TelevisionShowDomainModel();
        televisionShow.setId(1);
        List<TelevisionShowCreditDomainModel> cast = new ArrayList<>();
        List<TelevisionShowCreditDomainModel> crew = new ArrayList<>();
        List<TelevisionShowDomainModel> similarTelevisionShows = new ArrayList<>();
        String rating = "";
        televisionShowDetailsDomainModel.setTelevisionShow(televisionShow);
        televisionShowDetailsDomainModel.setCast(cast);
        televisionShowDetailsDomainModel.setCrew(crew);
        televisionShowDetailsDomainModel.setSimilarTelevisionShows(similarTelevisionShows);
        televisionShowDetailsDomainModel.setRating(rating);
        return televisionShowDetailsDomainModel;
    }
    // endregion
}
