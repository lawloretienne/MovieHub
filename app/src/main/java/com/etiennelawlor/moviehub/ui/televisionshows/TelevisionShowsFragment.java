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
import com.etiennelawlor.moviehub.data.local.sharedpreferences.PreferencesHelper;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.ui.base.BaseAdapter;
import com.etiennelawlor.moviehub.ui.base.BaseFragment;
import com.etiennelawlor.moviehub.ui.televisionshowdetails.TelevisionShowDetailsActivity;
import com.etiennelawlor.moviehub.util.FontCache;
import com.etiennelawlor.moviehub.util.NetworkLogUtility;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowsFragment extends BaseFragment implements TelevisionShowsAdapter.OnItemClickListener, TelevisionShowsAdapter.OnReloadClickListener, TelevisionShowsContract.View {

    // region Constants
    public static final String KEY_TELEVISION_SHOW = "KEY_TELEVISION_SHOW";
    private static final int PAGE_SIZE = 20;
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
    // endregion

    // region Member Variables
    private TelevisionShowsAdapter televisionShowsAdapter;
    private Typeface font;
    private MovieHubService movieHubService;
    private Unbinder unbinder;
    private StaggeredGridLayoutManager layoutManager;
    private Configuration configuration;
    private CompositeSubscription compositeSubscription;
    private int currentPage = 1;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    // endregion

    // region Listeners
    @OnClick(R.id.reload_btn)
    public void onReloadButtonClicked() {
        emptyLinearLayout.setVisibility(View.GONE);
        errorLinearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        addPopularTelevisionShowsSubscription();
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
            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItem) >= totalItemCount && totalItemCount > 0) {
                    loadMoreItems();
                }
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

        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(getContext()));

        font = FontCache.getTypeface("Lato-Medium.ttf", getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_television_shows, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        compositeSubscription = new CompositeSubscription();

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

        configuration = PreferencesHelper.getConfiguration(getContext());

        if(configuration != null){
            addPopularTelevisionShowsSubscription();
        } else {
            Subscription subscription = movieHubService.getConfiguration()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Configuration>() {
                        @Override
                        public void call(Configuration configuration) {
                            if(configuration != null){
                                PreferencesHelper.setConfiguration(getContext(), configuration);

                                addPopularTelevisionShowsSubscription();
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                            if (NetworkUtility.isKnownException(throwable)) {
                                errorTextView.setText("Can't load data.\nCheck your network connection.");
                                errorLinearLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    });
            compositeSubscription.add(subscription);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        compositeSubscription.unsubscribe();
        unbinder.unbind();
    }

    // endregion

    // region TelevisionShowsAdapter.OnItemClickListener Methods
    @Override
    public void onItemClick(int position, View view) {
        TelevisionShow televisionShow = televisionShowsAdapter.getItem(position);
        if(televisionShow != null){
            Intent intent = new Intent(getActivity(), TelevisionShowDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_TELEVISION_SHOW, televisionShow);
            intent.putExtras(bundle);

            Window window = getActivity().getWindow();
//            window.setStatusBarColor(primaryDark);

            Resources resources = view.getResources();
            Pair<View, String> televisionShowPair  = getPair(view, resources.getString(R.string.transition_television_show_thumbnail));

            ActivityOptionsCompat options = getActivityOptionsCompat(televisionShowPair);

            window.setExitTransition(null);
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    }
    // endregion

    // region TelevisionShowsAdapter.OnReloadClickListener Methods
    @Override
    public void onReloadClick() {
        televisionShowsAdapter.updateFooter(BaseAdapter.FooterType.LOAD_MORE);

        addPopularTelevisionShowsSubscription();
    }
    // endregion

    // region Helper Methods
    private void removeListeners() {
        televisionShowsAdapter.setOnItemClickListener(null);
    }

    private void loadMoreItems() {
        isLoading = true;
        currentPage += 1;

        addPopularTelevisionShowsSubscription();
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

    private void addPopularTelevisionShowsSubscription(){
        Subscription popularTelevisionShowsSubscription = movieHubService.getPopularTelevisionShows(currentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TelevisionShowsEnvelope>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(currentPage == 1){
                            isLoading = false;
                            progressBar.setVisibility(View.GONE);

                            if(NetworkUtility.isKnownException(throwable)){
                                errorTextView.setText("Can't load data.\nCheck your network connection.");
                                errorLinearLayout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if(NetworkUtility.isKnownException(throwable)){
                                televisionShowsAdapter.updateFooter(BaseAdapter.FooterType.ERROR);
                            }
                        }
                    }

                    @Override
                    public void onNext(TelevisionShowsEnvelope televisionShowsEnvelope) {
                        if(currentPage == 1){
                            progressBar.setVisibility(View.GONE);
                            isLoading = false;

                            if(televisionShowsEnvelope != null){
                                List<TelevisionShow> televisionShows = televisionShowsEnvelope.getTelevisionShows();
                                if(televisionShows != null){
                                    if(televisionShows.size()>0)
                                        televisionShowsAdapter.addAll(televisionShows);

                                    if(televisionShows.size() >= PAGE_SIZE){
                                        televisionShowsAdapter.addFooter();
                                    } else {
                                        isLastPage = true;
                                    }
                                }
                            }

                            if(televisionShowsAdapter.isEmpty()){
                                emptyLinearLayout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            televisionShowsAdapter.removeFooter();
                            isLoading = false;

                            if(televisionShowsEnvelope != null){
                                List<TelevisionShow> televisionShows = televisionShowsEnvelope.getTelevisionShows();
                                if(televisionShows != null){
                                    if(televisionShows.size()>0)
                                        televisionShowsAdapter.addAll(televisionShows);

                                    if(televisionShows.size() >= PAGE_SIZE){
                                        televisionShowsAdapter.addFooter();
                                    } else {
                                        isLastPage = true;
                                    }
                                }
                            }
                        }
                    }
                });
        compositeSubscription.add(popularTelevisionShowsSubscription);

    }
    // endregion
}
