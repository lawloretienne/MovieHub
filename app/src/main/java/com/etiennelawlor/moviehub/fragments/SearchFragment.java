package com.etiennelawlor.moviehub.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.activities.MovieDetailsActivity;
import com.etiennelawlor.moviehub.activities.PersonDetailsActivity;
import com.etiennelawlor.moviehub.activities.TelevisionShowDetailsActivity;
import com.etiennelawlor.moviehub.adapters.BaseAdapter;
import com.etiennelawlor.moviehub.adapters.SearchMoviesAdapter;
import com.etiennelawlor.moviehub.adapters.SearchPersonsAdapter;
import com.etiennelawlor.moviehub.adapters.SearchTelevisionShowsAdapter;
import com.etiennelawlor.moviehub.models.FullSearchResponse;
import com.etiennelawlor.moviehub.network.MovieHubService;
import com.etiennelawlor.moviehub.network.ServiceGenerator;
import com.etiennelawlor.moviehub.network.interceptors.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.network.models.Movie;
import com.etiennelawlor.moviehub.network.models.MoviesEnvelope;
import com.etiennelawlor.moviehub.network.models.PeopleEnvelope;
import com.etiennelawlor.moviehub.network.models.Person;
import com.etiennelawlor.moviehub.network.models.TelevisionShow;
import com.etiennelawlor.moviehub.network.models.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.ui.GravitySnapHelper;
import com.etiennelawlor.moviehub.utilities.DisplayUtility;
import com.etiennelawlor.moviehub.utilities.FontCache;
import com.etiennelawlor.moviehub.utilities.NetworkUtility;
import com.etiennelawlor.moviehub.utilities.TrestleUtility;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 1/13/17.
 */

public class SearchFragment extends BaseFragment {

    // region Constants
    public static final String KEY_MOVIE = "KEY_MOVIE";
    public static final String KEY_TELEVISION_SHOW = "KEY_TELEVISION_SHOW";
    public static final String KEY_PERSON = "KEY_PERSON";
//    private static final int PAGE_SIZE = 20;
    // endregion

    // region Views
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_et)
    EditText searchEditText;
    @BindView(R.id.movies_ll)
    LinearLayout moviesLinearLayout;
    @BindView(R.id.movies_rv)
    RecyclerView moviesRecyclerView;
    @BindView(R.id.television_shows_ll)
    LinearLayout televisionShowsLinearLayout;
    @BindView(R.id.television_shows_rv)
    RecyclerView televisionShowsRecyclerView;
    @BindView(R.id.persons_ll)
    LinearLayout personsLinearLayout;
    @BindView(R.id.persons_rv)
    RecyclerView personsRecyclerView;
    @BindView(R.id.search_pb)
    ProgressBar searchProgressBar;
    @BindView(android.R.id.empty)
    LinearLayout emptyLinearLayout;
    @BindView(R.id.empty_tv)
    TextView emptyTextView;
    // endregion

    // region Member Variables
    private Typeface font;
    private MovieHubService movieHubService;
    private Unbinder unbinder;
    private SearchMoviesAdapter searchMoviesAdapter;
    private SearchTelevisionShowsAdapter searchTelevisionShowsAdapter;
    private SearchPersonsAdapter searchPersonsAdapter;
    private Observable<CharSequence> searchQueryChangeObservable;
    private CompositeSubscription compositeSubscription;
    private String query;
//    private int currentPage = 1;
//    private boolean isLastPage = false;
//    private boolean isLoading = false;
    private Transition sharedElementEnterTransition;
    private Transition sharedElementReturnTransition;
    // endregion

    // region Listeners
    private Transition.TransitionListener enterTransitionTransitionListener = new Transition.TransitionListener() {
        @Override
        public void onTransitionStart(Transition transition) {
            Timber.d("");
        }

        @Override
        public void onTransitionEnd(Transition transition) {
            DisplayUtility.showKeyboard(getContext(), searchEditText);
            searchEditText.animate().alpha(1.0f).setDuration(300);
        }

        @Override
        public void onTransitionCancel(Transition transition) {

        }

        @Override
        public void onTransitionPause(Transition transition) {

        }

        @Override
        public void onTransitionResume(Transition transition) {

        }
    };

    private Transition.TransitionListener returnTransitionTransitionListener = new Transition.TransitionListener() {
        @Override
        public void onTransitionStart(Transition transition) {
            Timber.d("");
            searchEditText.animate().alpha(0.0f).setDuration(300);
        }

        @Override
        public void onTransitionEnd(Transition transition) {
            appbar.animate().alpha(0.0f).setDuration(300);
        }

        @Override
        public void onTransitionCancel(Transition transition) {
        }

        @Override
        public void onTransitionPause(Transition transition) {
        }

        @Override
        public void onTransitionResume(Transition transition) {
        }
    };

    private BaseAdapter.OnItemClickListener searchMoviesAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            Movie movie = searchMoviesAdapter.getItem(position);
            if(movie != null){
                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_MOVIE, movie);
//                bundle.putInt(MovieDetailsActivity.KEY_STATUS_BAR_COLOR, getActivity().getWindow().getStatusBarColor());
                intent.putExtras(bundle);

                Window window = getActivity().getWindow();
//                window.setStatusBarColor(statusBarColor);

                Resources resources = view.getResources();
                Pair<View, String> moviePair  = getPair(view, resources.getString(R.string.transition_movie_thumbnail));

                ActivityOptionsCompat options = getActivityOptionsCompat(moviePair);

                window.setExitTransition(null);
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        }
    };

    private BaseAdapter.OnItemClickListener searchTelevisionShowsAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            TelevisionShow televisionShow = searchTelevisionShowsAdapter.getItem(position);
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
    };


    private BaseAdapter.OnItemClickListener searchPersonsAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            Person person = searchPersonsAdapter.getItem(position);
            if(person != null){
                Intent intent = new Intent(getActivity(), PersonDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_PERSON, person);
                intent.putExtras(bundle);

                Window window = getActivity().getWindow();
//            window.setStatusBarColor(primaryDark);

                Resources resources = view.getResources();
                Pair<View, String> personPair  = getPair(view, resources.getString(R.string.transition_person_thumbnail));

                ActivityOptionsCompat options = getActivityOptionsCompat(personPair);

                window.setExitTransition(null);
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        }
    };

//
//    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//        }
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//
//            int visibleItemCount = recyclerView.getChildCount();
//            int totalItemCount = recyclerView.getAdapter().getItemCount();
//            int[] positions = layoutManager.findFirstVisibleItemPositions(null);
//            int firstVisibleItem = positions[1];
//            if (!isLoading && !isLastPage) {
//                if ((visibleItemCount + firstVisibleItem) >= totalItemCount && totalItemCount > 0) {
//                    loadMoreItems();
//                }
//            }
//        }
//    };

    // endregion

    // region Callbacks
    // endregion

    // region Constructors
    public SearchFragment() {
    }
    // endregion

    // region Factory Methods
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public static SearchFragment newInstance(Bundle extras) {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        compositeSubscription = new CompositeSubscription();

        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(getContext()));

        setHasOptionsMenu(true);

        sharedElementEnterTransition = getActivity().getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(enterTransitionTransitionListener);
//        sharedElementReturnTransition = getActivity().getWindow().getSharedElementReturnTransition();
//        sharedElementReturnTransition.addListener(returnTransitionTransitionListener);


        font = FontCache.getTypeface("Lato-Medium.ttf", getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        searchQueryChangeObservable = RxTextView.textChanges(searchEditText);

        setUpSearchQuerySubscription();

        setUpMoviesLayout();
        setUpTelevisionShowsLayout();
        setUpPeopleLayout();
    }

    private void setUpMoviesLayout(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        moviesRecyclerView.setLayoutManager(layoutManager);
        searchMoviesAdapter = new SearchMoviesAdapter(getContext());
//        moviesRecyclerView.setItemAnimator(new SlideInRightAnimator());
        searchMoviesAdapter.setOnItemClickListener(searchMoviesAdapterOnItemClickListener);
        moviesRecyclerView.setAdapter(searchMoviesAdapter);
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(moviesRecyclerView);
    }

    private void setUpTelevisionShowsLayout(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        televisionShowsRecyclerView.setLayoutManager(layoutManager);
        searchTelevisionShowsAdapter = new SearchTelevisionShowsAdapter(getContext());
//        televisionShowsRecyclerView.setItemAnimator(new SlideInRightAnimator());
        searchTelevisionShowsAdapter.setOnItemClickListener(searchTelevisionShowsAdapterOnItemClickListener);
        televisionShowsRecyclerView.setAdapter(searchTelevisionShowsAdapter);
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(televisionShowsRecyclerView);
    }

    private void setUpPeopleLayout(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        personsRecyclerView.setLayoutManager(layoutManager);
        searchPersonsAdapter = new SearchPersonsAdapter(getContext());
//        personsRecyclerView.setItemAnimator(new SlideInRightAnimator());
        searchPersonsAdapter.setOnItemClickListener(searchPersonsAdapterOnItemClickListener);
        personsRecyclerView.setAdapter(searchPersonsAdapter);
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(personsRecyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        searchEditText.animate().alpha(0.0f).setDuration(300);

        removeListeners();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeSubscription.unsubscribe();
    }

    // endregion

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                DisplayUtility.hideKeyboard(getContext(), searchEditText);
//                searchEditText.animate().alpha(0.0f).setDuration(300);
                getActivity().supportFinishAfterTransition();
//                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // region Helper Methods
    private void removeListeners() {
        sharedElementEnterTransition.removeListener(enterTransitionTransitionListener);
//        sharedElementReturnTransition.removeListener(returnTransitionTransitionListener);
//        moviesAdapter.setOnItemClickListener(null);
    }

    private void setUpSearchQuerySubscription(){
        Subscription searchQuerySubscription = searchQueryChangeObservable
                .doOnNext(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        searchProgressBar.setVisibility(View.INVISIBLE);
                    }
                })
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        if(TextUtils.isEmpty(charSequence)){
                            searchProgressBar.setVisibility(View.INVISIBLE);

                            searchMoviesAdapter.clear();
                            moviesLinearLayout.setVisibility(View.GONE);
                            searchTelevisionShowsAdapter.clear();
                            televisionShowsLinearLayout.setVisibility(View.GONE);
                            searchPersonsAdapter.clear();
                            personsLinearLayout.setVisibility(View.GONE);
                            emptyLinearLayout.setVisibility(View.GONE);
                        } else {
                            searchProgressBar.setVisibility(View.VISIBLE);
                        }

                        return !TextUtils.isEmpty(charSequence);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {
                        return charSequence.toString();
                    }
                })
                .switchMap(new Func1<String, Observable<FullSearchResponse>>() {
                    @Override
                    public Observable<FullSearchResponse> call(String q) {
                        query = q;

                        return Observable.combineLatest(movieHubService.searchMovies(query, 1),
                                movieHubService.searchTelevisionShows(query, 1),
                                movieHubService.searchPeople(query, 1),
                                new Func3<MoviesEnvelope, TelevisionShowsEnvelope, PeopleEnvelope, FullSearchResponse>() {
                                    @Override
                                    public FullSearchResponse call(MoviesEnvelope moviesEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope, PeopleEnvelope peopleEnvelope) {
                                        return new FullSearchResponse(moviesEnvelope, televisionShowsEnvelope, peopleEnvelope);
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<FullSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable t) {
                        searchProgressBar.setVisibility(View.INVISIBLE);
//                        t.printStackTrace();

                        if (NetworkUtility.isKnownException(t)) {
                            Snackbar snackbar = Snackbar.make(ButterKnife.findById(getActivity(), R.id.main_content),
                                TrestleUtility.getFormattedText("Network connection is unavailable.", font, 16),
                                Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    setUpSearchQuerySubscription();
                                }
                            });
                            View snackBarView = snackbar.getView();
//                            snackBarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey_200));
                            TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.secondary_text_light));
                            textView.setTypeface(font);

                            snackbar.show();
                        }
                    }

                    @Override
                    public void onNext(FullSearchResponse fullSearchResponse) {
                        searchProgressBar.setVisibility(View.INVISIBLE);

                        if (fullSearchResponse != null) {

                            setUpMovies(fullSearchResponse.getMoviesEnvelope());
                            setUpTelevisionShows(fullSearchResponse.getTelevisionShowsEnvelope());
                            setUpPeople(fullSearchResponse.getPeopleEnvelope());

                            if(fullSearchResponse.hasResults()){
                                emptyLinearLayout.setVisibility(View.GONE);
                            } else {
                                emptyTextView.setText(String.format("No results found for \"%s\"", query));
                                emptyLinearLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
        compositeSubscription.add(searchQuerySubscription);
    }

    private void setUpMovies(MoviesEnvelope movieEnvelope){
        if(movieEnvelope != null){
            List<Movie> movies = movieEnvelope.getMovies();
            if(movies != null && movies.size()>0){
                searchMoviesAdapter.clear();
                searchMoviesAdapter.addAll(movies);
                moviesLinearLayout.setVisibility(View.VISIBLE);

//                if(searchMoviesAdapter.isEmpty()){
//                    searchMoviesAdapter.addAll(movies);
//                } else {
//                    searchMoviesAdapter.updatedAdapter(movies);
//                }
            } else {
                searchMoviesAdapter.clear();
                moviesLinearLayout.setVisibility(View.GONE);
            }
        }
    }

    private void setUpTelevisionShows(TelevisionShowsEnvelope televisionShowsEnvelope){
        if(televisionShowsEnvelope != null){
            List<TelevisionShow> televisionShows = televisionShowsEnvelope.getTelevisionShows();
            if(televisionShows != null && televisionShows.size()>0){
                searchTelevisionShowsAdapter.clear();
                searchTelevisionShowsAdapter.addAll(televisionShows);
                televisionShowsLinearLayout.setVisibility(View.VISIBLE);
            } else {
                searchTelevisionShowsAdapter.clear();
                televisionShowsLinearLayout.setVisibility(View.GONE);
            }
        }
    }

    private void setUpPeople(PeopleEnvelope peopleEnvelope){
        if(peopleEnvelope != null){
            List<Person> persons = peopleEnvelope.getPersons();
            if(persons != null && persons.size()>0){
                searchPersonsAdapter.clear();
                searchPersonsAdapter.addAll(persons);
                personsLinearLayout.setVisibility(View.VISIBLE);
            } else {
                searchPersonsAdapter.clear();
                personsLinearLayout.setVisibility(View.GONE);
            }
        }
    }

//    private void loadMoreItems() {
//        isLoading = true;
//        currentPage += 1;
//
//        Call getPopularMoviesCall = movieHubService.getPopularMovies(currentPage);
//        calls.add(getPopularMoviesCall);
//        getPopularMoviesCall.enqueue(getPopularMoviesNextFetchCallback);
//    }

    private ActivityOptionsCompat getActivityOptionsCompat(Pair pair){
        ActivityOptionsCompat options = null;

        Pair<View, String> statusBarPair = getStatusBarPair();
        Pair<View, String> navigationBarPair  = getNavigationBarPair();
        Pair<View, String> appBarPair  = getAppBarPair();

        if(pair!=null
                && statusBarPair!= null
                && navigationBarPair!= null
                && appBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, statusBarPair, navigationBarPair, appBarPair);
        } else if(pair != null
                && statusBarPair != null
                && appBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, statusBarPair, appBarPair);
        } else if(pair != null
                && navigationBarPair != null
                && appBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, navigationBarPair, appBarPair);
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
