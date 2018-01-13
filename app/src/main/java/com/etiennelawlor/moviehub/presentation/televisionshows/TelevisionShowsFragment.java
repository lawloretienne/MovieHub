package com.etiennelawlor.moviehub.presentation.televisionshows;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.di.component.TelevisionShowsComponent;
import com.etiennelawlor.moviehub.di.module.TelevisionShowsModule;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BaseAdapter;
import com.etiennelawlor.moviehub.presentation.base.BaseFragment;
import com.etiennelawlor.moviehub.presentation.mappers.TelevisionShowPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.mappers.TelevisionShowsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowsPresentationModel;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsActivity;
import com.etiennelawlor.moviehub.util.FontCache;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowsFragment extends BaseFragment implements TelevisionShowsAdapter.OnItemClickListener, TelevisionShowsAdapter.OnReloadClickListener, TelevisionShowsPresentationContract.View {

    // region Views
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.error_ll)
    LinearLayout errorLinearLayout;
    @BindView(R.id.error_tv)
    TextView errorTextView;
    @BindView(R.id.pb)
    ProgressBar progressBar;
    @BindView(android.R.id.empty)
    LinearLayout emptyLinearLayout;

    private View selectedTelevisionShowView;
    // endregion

    // region Member Variables
    private TelevisionShowsAdapter televisionShowsAdapter;
    private Unbinder unbinder;
    private StaggeredGridLayoutManager layoutManager;
    private boolean isLoading = false;
    private TelevisionShowsPresentationModel televisionShowsPresentationModel;
    private TelevisionShowsComponent televisionShowsComponent;
    private TelevisionShowsPresentationModelMapper televisionShowsPresentationModelMapper = new TelevisionShowsPresentationModelMapper();
    private TelevisionShowPresentationModelMapper televisionShowPresentationModelMapper = new TelevisionShowPresentationModelMapper();

    // endregion

    // region Injected Variables
    @Inject
    TelevisionShowsPresentationContract.Presenter televisionShowsPresenter;
    // endregion

    // region Listeners
    @OnClick(R.id.reload_btn)
    public void onReloadButtonClicked() {
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsPresentationModel == null ? 1 : televisionShowsPresentationModel.getPageNumber());
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = recyclerView.getAdapter().getItemCount();
            int[] positions = layoutManager.findFirstVisibleItemPositions(null);
            int firstVisibleItem = positions[1];

            if ((visibleItemCount + firstVisibleItem) >= totalItemCount
                    && totalItemCount > 0
                    && !isLoading
                    && !televisionShowsPresentationModel.isLastPage()) {
                televisionShowsPresenter.onScrollToEndOfList();
            }
        }
    };

    // endregion

    // region Constructors
    public TelevisionShowsFragment() {
    }
    // endregion

    // region Factory Methods
    public static TelevisionShowsFragment newInstance() {
        return new TelevisionShowsFragment();
    }

    public static TelevisionShowsFragment newInstance(Bundle extras) {
        TelevisionShowsFragment fragment = new TelevisionShowsFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createTelevisionShowsComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_television_shows, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        televisionShowsAdapter = new TelevisionShowsAdapter(getContext());
        televisionShowsAdapter.setOnItemClickListener(this);
        televisionShowsAdapter.setOnReloadClickListener(this);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setAdapter(televisionShowsAdapter);

        // Pagination
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsPresentationModel == null ? 1 : televisionShowsPresentationModel.getPageNumber());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        unbinder.unbind();
        televisionShowsPresenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        releaseTelevisionShowsComponent();
    }
    // endregion

    // region TelevisionShowsAdapter.OnItemClickListener Methods
    @Override
    public void onItemClick(int position, View view) {
        selectedTelevisionShowView = view;

        TelevisionShowPresentationModel televisionShow = televisionShowsAdapter.getItem(position);
        if(televisionShow != null){
            televisionShowsPresenter.onTelevisionShowClick(televisionShow);
        }
    }
    // endregion

    // region TelevisionShowsAdapter.OnReloadClickListener Methods
    @Override
    public void onReloadClick() {
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsPresentationModel.getPageNumber());
    }
    // endregion

    // region TelevisionShowsPresentationContract.View Methods

    @Override
    public void showEmptyView() {
        emptyLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        errorLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorView() {
        errorLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void setErrorText(String errorText) {
        errorTextView.setText(errorText);
    }

    @Override
    public void showLoadingView() {
        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;
    }

    @Override
    public void hideLoadingView() {
        progressBar.setVisibility(View.GONE);
        isLoading = false;
    }

    @Override
    public void addHeader() {
        televisionShowsAdapter.addHeader();
    }

    @Override
    public void addFooter() {
        televisionShowsAdapter.addFooter();
    }

    @Override
    public void removeFooter() {
        televisionShowsAdapter.removeFooter();
        isLoading = false;
    }

    @Override
    public void showErrorFooter() {
        televisionShowsAdapter.updateFooter(BaseAdapter.FooterType.ERROR);
    }

    @Override
    public void showLoadingFooter() {
        televisionShowsAdapter.updateFooter(BaseAdapter.FooterType.LOAD_MORE);
        isLoading = true;
    }

    @Override
    public void addTelevisionShowsToAdapter(List<TelevisionShowDomainModel> televisionShows) {
        televisionShowsAdapter.addAll(televisionShowPresentationModelMapper.mapListToPresentationModelList(televisionShows));
    }

    @Override
    public void loadMoreItems() {
        televisionShowsPresentationModel.incrementPageNumber();
        televisionShowsPresenter.onLoadPopularTelevisionShows(televisionShowsPresentationModel.getPageNumber());
    }

    @Override
    public void setTelevisionShowsDomainModel(TelevisionShowsDomainModel televisionShowsDomainModel) {
        this.televisionShowsPresentationModel = televisionShowsPresentationModelMapper.mapToPresentationModel(televisionShowsDomainModel);
    }

    @Override
    public void openTelevisionShowDetails(TelevisionShowPresentationModel televisionShow) {
        Window window = getActivity().getWindow();
//            window.setStatusBarColor(primaryDark);

        Pair<View, String> televisionShowPair  = getTelevisionShowPair();
        ActivityOptionsCompat options = getActivityOptionsCompat(televisionShowPair);

        window.setExitTransition(null);
        ActivityCompat.startActivity(getActivity(), TelevisionShowDetailsActivity.createIntent(getContext(), televisionShow), options.toBundle());
    }
    // endregion

    // region Helper Methods
    private void removeListeners() {
        televisionShowsAdapter.setOnItemClickListener(null);
    }

    private ActivityOptionsCompat getActivityOptionsCompat(Pair pair){
        ActivityOptionsCompat options = null;

        Pair<View, String> bottomNavigationViewPair = getBottomNavigationViewPair();
        Pair<View, String> statusBarPair = getStatusBarPair();
        Pair<View, String> navigationBarPair  = getNavigationBarPair();
        Pair<View, String> appBarPair  = getAppBarPair();

        if(pair!=null
                && bottomNavigationViewPair != null
                && statusBarPair!= null
                && navigationBarPair!= null
                && appBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, bottomNavigationViewPair, statusBarPair, navigationBarPair, appBarPair);
        } else if(pair != null
                && bottomNavigationViewPair != null
                && statusBarPair != null
                && appBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, bottomNavigationViewPair, statusBarPair, appBarPair);
        } else if(pair != null
                && bottomNavigationViewPair != null
                && navigationBarPair != null
                && appBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, bottomNavigationViewPair, navigationBarPair, appBarPair);
        }

        return options;
    }

    private Pair<View, String> getTelevisionShowPair(){
        Resources resources = getResources();
        String transitionName = resources.getString(R.string.transition_television_show_thumbnail);
        View view = selectedTelevisionShowView.findViewById(R.id.thumbnail_iv);
        return getPair(view, transitionName);
    }

    private Pair<View, String> getBottomNavigationViewPair(){
        Resources resources = getResources();
        String transitionName = resources.getString(R.string.transition_bottom_navigation);
        View view = getActivity().findViewById(R.id.bottom_navigation);
        return getPair(view, transitionName);
    }

    private Pair<View, String> getStatusBarPair(){
        View view = getActivity().findViewById(android.R.id.statusBarBackground);
        return getPair(view);
    }

    private Pair<View, String> getNavigationBarPair(){
        View view = getActivity().findViewById(android.R.id.navigationBarBackground);
        return getPair(view);
    }

    private Pair<View, String> getAppBarPair(){
        Resources resources = getResources();
        String transitionName = resources.getString(R.string.transition_app_bar);
        View view = getActivity().findViewById(R.id.appbar);
        return getPair(view, transitionName);
    }

    private Pair<View, String> getPair(View view, String transitionName){
        Pair<View, String> pair = null;
        if(view != null) {
            pair = Pair.create(view, transitionName);
        }
        return pair;
    }

    private Pair<View, String> getPair(View view){
        Pair<View, String> pair = null;
        if(view != null) {
            pair = Pair.create(view, view.getTransitionName());
        }
        return pair;
    }

    public void scrollToTop(){
        recyclerView.scrollToPosition(0);
    }

    private TelevisionShowsComponent createTelevisionShowsComponent(){
        televisionShowsComponent = ((MovieHubApplication)getActivity().getApplication())
                .getApplicationComponent()
                .createSubcomponent(new TelevisionShowsModule(this));
        return televisionShowsComponent;
    }

    public void releaseTelevisionShowsComponent(){
        televisionShowsComponent = null;
    }
    // endregion
}
