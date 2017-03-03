package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.model.TelevisionShowsModel;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.source.televisionshows.TelevisionShowsDataSourceContract;
import com.etiennelawlor.moviehub.ui.televisionshows.TelevisionShowsPresenter;
import com.etiennelawlor.moviehub.ui.televisionshows.TelevisionShowsUIContract;
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
    private TelevisionShowsUIContract.View mockTelevisionShowsView;
    @Mock
    private TelevisionShowsDataSourceContract.Repository mockTelevisionShowsRepository;

    // Stubs
    private Observable stub;

    // endregion

    // region Member Variables
    private TelevisionShowsModel televisionShowsModel;
    private TelevisionShowsPresenter televisionShowsPresenter;
    // endregion

    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        televisionShowsPresenter = new TelevisionShowsPresenter(mockTelevisionShowsView, mockTelevisionShowsRepository, new TestSchedulerTransformer<TelevisionShowsModel>());
    }

    // region Test Methods
//    @Test(expected = IOException.class)
    @Test
    public void onLoadPopularTelevisionShows_shouldShowError_whenFirstPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(0), 1, true);
        stub = Observable.<TelevisionShowsModel>error(new IOException());
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).setErrorText(anyString());
        verify(mockTelevisionShowsView).showErrorView();
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldShowError_whenNextPageRequestFailed() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(0), 2, true);
        stub = Observable.<TelevisionShowsModel>error(new IOException());
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooter();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).showErrorFooter();
    }

    @Test
    public void onLoadPopularTelevisionShowss_shouldShowEmpty_whenFirstPageHasNoTelevisionShows() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(0), 1, true);
        stub = Observable.just(televisionShowsModel);
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).showEmptyView();
        verify(mockTelevisionShowsView).setModel(televisionShowsModel);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldNotAddTelevisionShows_whenNextPageHasNoTelevisionShows() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(0), 2, true);
        stub = Observable.just(televisionShowsModel);
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooter();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).removeFooter();
        verify(mockTelevisionShowsView).setModel(televisionShowsModel);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenFirstPageHasTelevisionShowsAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(5), 1, true);
        stub = Observable.just(televisionShowsModel);
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).addTelevisionShowsToAdapter(televisionShowsModel.getTelevisionShows());
        verify(mockTelevisionShowsView).setModel(televisionShowsModel);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenFirstPageHasTelevisionShowsAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(5), 1, false);
        stub = Observable.just(televisionShowsModel);
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).hideEmptyView();
        verify(mockTelevisionShowsView).hideErrorView();
        verify(mockTelevisionShowsView).showLoadingView();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).hideLoadingView();
        verify(mockTelevisionShowsView).addTelevisionShowsToAdapter(televisionShowsModel.getTelevisionShows());
        verify(mockTelevisionShowsView).addFooter();
        verify(mockTelevisionShowsView).setModel(televisionShowsModel);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenNextPageHasTelevisionShowsAndIsLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(5), 2, true);
        stub = Observable.just(televisionShowsModel);
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooter();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).removeFooter();
        verify(mockTelevisionShowsView).addTelevisionShowsToAdapter(televisionShowsModel.getTelevisionShows());
        verify(mockTelevisionShowsView).setModel(televisionShowsModel);
    }

    @Test
    public void onLoadPopularTelevisionShows_shouldAddTelevisionShows_whenNextPageHasTelevisionShowsAndIsNotLastPage() {
        // 1. (Given) Set up conditions required for the test
        televisionShowsModel = new TelevisionShowsModel(getListOfTelevisionShows(5), 2, false);
        stub = Observable.just(televisionShowsModel);
        when(mockTelevisionShowsRepository.getPopularTelevisionShows(anyInt())).thenReturn(stub);

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsModel.getCurrentPage());

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).showLoadingFooter();

        verify(mockTelevisionShowsRepository).getPopularTelevisionShows(anyInt());

        verify(mockTelevisionShowsView).removeFooter();
        verify(mockTelevisionShowsView).addTelevisionShowsToAdapter(televisionShowsModel.getTelevisionShows());
        verify(mockTelevisionShowsView).addFooter();
        verify(mockTelevisionShowsView).setModel(televisionShowsModel);
//        verify(mockTelevisionShowsView, times(1)).setModel(any(TelevisionShowsModel.class)); // Alternative verify check
    }

    @Test
    public void onTelevisionShowClick_shouldOpenTelevisionShowDetails() {
        // 1. (Given) Set up conditions required for the test
        TelevisionShow televisionShow = new TelevisionShow();

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onTelevisionShowClick(televisionShow);

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).openTelevisionShowDetails(televisionShow);

        verifyZeroInteractions(mockTelevisionShowsRepository);
    }

    @Test
    public void onScrollToEndOfList_shouldLoadMoreItems() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onScrollToEndOfList();

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verify(mockTelevisionShowsView).loadMoreItems();

        verifyZeroInteractions(mockTelevisionShowsRepository);
    }

    @Test
    public void onDestroyView_shouldNotHaveInteractions() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
        televisionShowsPresenter.onDestroyView();
        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        verifyZeroInteractions(mockTelevisionShowsView);
        verifyZeroInteractions(mockTelevisionShowsRepository);
    }

    // endregion

    // region Helper Methods
    private List<TelevisionShow> getListOfTelevisionShows(int numOfTelevisionShows){
        List<TelevisionShow> televisionShows = new ArrayList<>();
        for(int i=0; i<numOfTelevisionShows; i++){
            TelevisionShow televisionShow = new TelevisionShow();
            televisionShows.add(televisionShow);
        }
        return televisionShows;
    }
    // endregion
}
