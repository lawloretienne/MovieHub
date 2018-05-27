package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsPresentationContract;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsPresenter;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowsPresenterTest {

    // region Test Doubles

    // Mocks
    @Mock
    private TelevisionShowsPresentationContract.View mockTelevisionShowsView;
    @Mock
    private TelevisionShowsDomainContract.UseCase mockTelevisionShowsUseCase;

    // Stubs
    private TelevisionShowsDomainModel televisionShowsDomainModelStub;
    // endregion

    // region Member Variables
    private TelevisionShowsPresenter televisionShowsPresenter;
    private SchedulerProvider schedulerProvider = new TestSchedulerProvider();
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        televisionShowsPresenter = new TelevisionShowsPresenter(mockTelevisionShowsView, mockTelevisionShowsUseCase, schedulerProvider);
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadPopularTelevisionShows_shouldShowError_whenFirstPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(0, 1, true);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).showErrorView();
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldShowError_whenNextPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(0, 2, true);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.error(new IOException()));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooterView();

        verify(mockTelevisionShowsView).showErrorFooterView();
    }

    @Test
    public void onLoadPopularTelevisionShowss_shouldShowEmpty_whenFirstPageHasNoTelevisionShows() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(0, 1, true);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.just(televisionShowsDomainModelStub));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).showEmptyView();
        verify(mockTelevisionShowsView).setTelevisionShowsDomainModel(televisionShowsDomainModelStub);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldNotAddTelevisionShows_whenNextPageHasNoTelevisionShows() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(0, 2, true);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.just(televisionShowsDomainModelStub));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooterView();

        verify(mockTelevisionShowsView).removeFooterView();
        verify(mockTelevisionShowsView).setTelevisionShowsDomainModel(televisionShowsDomainModelStub);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenFirstPageHasTelevisionShowsAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(5, 1, true);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.just(televisionShowsDomainModelStub));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).addHeaderView();
        verify(mockTelevisionShowsView).showTelevisionShows(televisionShowsDomainModelStub.getTelevisionShows());
        verify(mockTelevisionShowsView).setTelevisionShowsDomainModel(televisionShowsDomainModelStub);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenFirstPageHasTelevisionShowsAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(5, 1, false);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.just(televisionShowsDomainModelStub));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).addHeaderView();
        verify(mockTelevisionShowsView).showTelevisionShows(televisionShowsDomainModelStub.getTelevisionShows());
        verify(mockTelevisionShowsView).addFooterView();
        verify(mockTelevisionShowsView).setTelevisionShowsDomainModel(televisionShowsDomainModelStub);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenNextPageHasTelevisionShowsAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(5, 2, true);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.just(televisionShowsDomainModelStub));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooterView();

        verify(mockTelevisionShowsView).removeFooterView();
        verify(mockTelevisionShowsView).showTelevisionShows(televisionShowsDomainModelStub.getTelevisionShows());
        verify(mockTelevisionShowsView).setTelevisionShowsDomainModel(televisionShowsDomainModelStub);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenNextPageHasTelevisionShowsAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsDomainModelStub = getTelevisionShowsDomainModelStub(5, 2, false);

        when(mockTelevisionShowsUseCase.getPopularTelevisionShows(anyInt())).thenReturn(Single.just(televisionShowsDomainModelStub));

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsDomainModelStub.getPageNumber());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooterView();

        verify(mockTelevisionShowsView).removeFooterView();
        verify(mockTelevisionShowsView).showTelevisionShows(televisionShowsDomainModelStub.getTelevisionShows());
        verify(mockTelevisionShowsView).addFooterView();
        verify(mockTelevisionShowsView).setTelevisionShowsDomainModel(televisionShowsDomainModelStub);
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShowPresentationModel televisionShow = new TelevisionShowPresentationModel();

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockTelevisionShowsUseCase);
    }

    @Test
    public void onScrollToEndOfList_shouldLoadMoreItems() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onScrollToEndOfList();

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).loadMoreTelevisionShows();

        verifyZeroInteractions(mockTelevisionShowsUseCase);
    }

    @Test
    public void onDestroyView_shouldNotInteractWithViewOrUsecase() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockTelevisionShowsView);
        verifyZeroInteractions(mockTelevisionShowsUseCase);
    }

    // endregion

    // region Helper Methods
    private List<TelevisionShowDomainModel> getListOfTelevisionShows(int numOfTelevisionShows){
        List<TelevisionShowDomainModel> televisionShows = new ArrayList<>();
        for(int i=0; i<numOfTelevisionShows; i++){
            TelevisionShowDomainModel televisionShow = new TelevisionShowDomainModel();
            televisionShows.add(televisionShow);
        }
        return televisionShows;
    }

    private TelevisionShowsDomainModel getTelevisionShowsDomainModelStub(int numOfTelevisionShows, int pageNumber, boolean lastPage){
        TelevisionShowsDomainModel televisionShowsDomainModel = new TelevisionShowsDomainModel();
        televisionShowsDomainModel.setTelevisionShows(getListOfTelevisionShows(numOfTelevisionShows));
        televisionShowsDomainModel.setPageNumber(pageNumber);
        televisionShowsDomainModel.setLastPage(lastPage);
        televisionShowsDomainModel.setExpiredAt(Calendar.getInstance().getTime());
        return televisionShowsDomainModel;
    }
    // endregion
}
