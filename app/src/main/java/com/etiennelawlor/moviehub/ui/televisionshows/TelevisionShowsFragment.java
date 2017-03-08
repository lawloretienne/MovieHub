package com.etiennelawlor.moviehub.ui.televisionshows;

import android.content.Intent;
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

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.data.model.PagingInfo;
import com.etiennelawlor.moviehub.data.model.TelevisionShowsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.source.televisionshows.TelevisionShowsLocalDataSource;
import com.etiennelawlor.moviehub.data.source.televisionshows.TelevisionShowsRemoteDataSource;
import com.etiennelawlor.moviehub.data.source.televisionshows.TelevisionShowsRepository;
import com.etiennelawlor.moviehub.ui.base.BaseAdapter;
import com.etiennelawlor.moviehub.ui.base.BaseFragment;
import com.etiennelawlor.moviehub.ui.televisionshowdetails.TelevisionShowDetailsActivity;
import com.etiennelawlor.moviehub.util.FontCache;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowsFragment extends BaseFragment implements TelevisionShowsAdapter.OnItemClickListener, TelevisionShowsAdapter.OnReloadClickListener, TelevisionShowsUiContract.View {

    // region Constants
    public static final String KEY_TELEVISION_SHOW = "KEY_TELEVISION_SHOW";
    // endregion

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
    private Typeface font;
    private Unbinder unbinder;
    private StaggeredGridLayoutManager layoutManager;
    private boolean isLoading = false;
    private PagingInfo pagingInfo;
    private TelevisionShowsUiContract.Presenter televisionShowsPresenter;
    // endregion

    // region Listeners
    @OnClick(R.id.reload_btn)
    public void onReloadButtonClicked() {
        televisionShowsPresenter.onLoadPopularTelevisionShows(pagingInfo == null ? 1 : pagingInfo.getCurrentPage());
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
                    && !pagingInfo.isLastPage()) {
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

        televisionShowsPresenter = new TelevisionShowsPresenter(
                this,
                new TelevisionShowsRepository(
                        new TelevisionShowsLocalDataSource(getContext()),
                        new TelevisionShowsRemoteDataSource(getContext())),
                new ProductionSchedulerTransformer<TelevisionShowsWrapper>()
        );

        font = FontCache.getTypeface("Lato-Medium.ttf", getContext());
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

        televisionShowsPresenter.onLoadPopularTelevisionShows(pagingInfo == null ? 1 : pagingInfo.getCurrentPage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        unbinder.unbind();
    }

    // endregion

    // region TelevisionShowsAdapter.OnItemClickListener Methods
    @Override
    public void onItemClick(int position, View view) {
        selectedTelevisionShowView = view;

        TelevisionShow televisionShow = televisionShowsAdapter.getItem(position);
        if(televisionShow != null){
            televisionShowsPresenter.onTelevisionShowClick(televisionShow);
        }
    }
    // endregion

    // region TelevisionShowsAdapter.OnReloadClickListener Methods
    @Override
    public void onReloadClick() {
        televisionShowsPresenter.onLoadPopularTelevisionShows(pagingInfo.getCurrentPage());
    }
    // endregion

    // region TelevisionShowsUiContract.View Methods

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
    public void addTelevisionShowsToAdapter(List<TelevisionShow> televisionShows) {
        televisionShowsAdapter.addAll(televisionShows);
    }

    @Override
    public void loadMoreItems() {
        pagingInfo.incrementPage();
        televisionShowsPresenter.onLoadPopularTelevisionShows(pagingInfo.getCurrentPage());
    }

    @Override
    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    @Override
    public void openTelevisionShowDetails(TelevisionShow televisionShow) {
        Intent intent = new Intent(getActivity(), TelevisionShowDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_TELEVISION_SHOW, televisionShow);
        intent.putExtras(bundle);

        Window window = getActivity().getWindow();
//            window.setStatusBarColor(primaryDark);

        Resources resources = selectedTelevisionShowView.getResources();
        Pair<View, String> televisionShowPair  = getPair(selectedTelevisionShowView, resources.getString(R.string.transition_television_show_thumbnail));

        ActivityOptionsCompat options = getActivityOptionsCompat(televisionShowPair);

        window.setExitTransition(null);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
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

    private Pair<View, String> getPair(View view, String transition){
        Pair<View, String> posterImagePair = null;
        View posterImageView = ButterKnife.findById(view, R.id.thumbnail_iv);
        if(posterImageView != null){
            posterImagePair = Pair.create(posterImageView, transition);
        }

        return posterImagePair;
    }

    private Pair<View, String> getBottomNavigationViewPair(){
        Pair<View, String> pair = null;
        View bottomNavigationView = ButterKnife.findById(getActivity(), R.id.bottom_navigation);
        if(bottomNavigationView != null) {
            Resources resources = bottomNavigationView.getResources();
            pair = Pair.create(bottomNavigationView, resources.getString(R.string.transition_bottom_navigation));
        }
        return pair;
    }

    private Pair<View, String> getStatusBarPair(){
        Pair<View, String> pair = null;
        View statusBar = ButterKnife.findById(getActivity(), android.R.id.statusBarBackground);
        if(statusBar != null)
            pair = Pair.create(statusBar, statusBar.getTransitionName());
        return pair;
    }

    private Pair<View, String> getNavigationBarPair(){
        Pair<View, String> pair = null;
        View navigationBar = ButterKnife.findById(getActivity(), android.R.id.navigationBarBackground);
        if(navigationBar != null)
            pair = Pair.create(navigationBar, navigationBar.getTransitionName());
        return pair;
    }

    private Pair<View, String> getAppBarPair(){
        Pair<View, String> pair = null;
        View appBar = ButterKnife.findById(getActivity(), R.id.appbar);
        if(appBar != null) {
            Resources resources = appBar.getResources();
            pair = Pair.create(appBar, resources.getString(R.string.transition_app_bar));
        }
        return pair;
    }
    // endregion
}
