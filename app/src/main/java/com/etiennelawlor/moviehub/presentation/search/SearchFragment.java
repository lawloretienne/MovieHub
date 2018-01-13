package com.etiennelawlor.moviehub.presentation.search;

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

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.di.component.SearchComponent;
import com.etiennelawlor.moviehub.di.module.SearchModule;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BaseAdapter;
import com.etiennelawlor.moviehub.presentation.base.BaseFragment;
import com.etiennelawlor.moviehub.presentation.common.GravitySnapHelper;
import com.etiennelawlor.moviehub.presentation.mappers.MoviePresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.mappers.PersonPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.mappers.TelevisionShowPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsActivity;
import com.etiennelawlor.moviehub.presentation.persondetails.PersonDetailsActivity;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsActivity;
import com.etiennelawlor.moviehub.util.DisplayUtility;
import com.etiennelawlor.moviehub.util.FontCache;
import com.etiennelawlor.moviehub.util.TrestleUtility;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 1/13/17.
 */

public class SearchFragment extends BaseFragment implements SearchPresentationContract.View {

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

    private View selectedMovieView;
    private View selectedTelevisionShowView;
    private View selectedPersonView;
    // endregion

    // region Member Variables
    private Typeface font;
    private Unbinder unbinder;
    private SearchMoviesAdapter searchMoviesAdapter;
    private SearchTelevisionShowsAdapter searchTelevisionShowsAdapter;
    private SearchPersonsAdapter searchPersonsAdapter;
    private InitialValueObservable<CharSequence> searchQueryChangeObservable;
    private Transition sharedElementEnterTransition;
    private Transition sharedElementReturnTransition;
    private SearchComponent searchComponent;
    private PersonPresentationModelMapper personPresentationModelMapper = new PersonPresentationModelMapper();
    private MoviePresentationModelMapper moviePresentationModelMapper = new MoviePresentationModelMapper();
    private TelevisionShowPresentationModelMapper televisionShowPresentationModelMapper = new TelevisionShowPresentationModelMapper();
    // endregion

    // region Injected Variables
    @Inject
    SearchPresentationContract.Presenter searchPresenter;
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
            selectedMovieView = view;

            MoviePresentationModel movie = searchMoviesAdapter.getItem(position);
            if(movie != null){
                searchPresenter.onMovieClick(movie);
            }
        }
    };

    private BaseAdapter.OnItemClickListener searchTelevisionShowsAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            selectedTelevisionShowView = view;

            TelevisionShowPresentationModel televisionShow = searchTelevisionShowsAdapter.getItem(position);
            if(televisionShow != null){
                searchPresenter.onTelevisionShowClick(televisionShow);
            }
        }
    };

    private BaseAdapter.OnItemClickListener searchPersonsAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            selectedPersonView = view;

            PersonPresentationModel person = searchPersonsAdapter.getItem(position);
            if(person != null){
                searchPresenter.onPersonClick(person);
            }
        }
    };

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

        createSearchComponent().inject(this);

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

        setUpMoviesLayout();
        setUpTelevisionShowsLayout();
        setUpPeopleLayout();

        searchQueryChangeObservable = RxTextView.textChanges(searchEditText);

        searchPresenter.onLoadSearch(searchQueryChangeObservable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        searchEditText.animate().alpha(0.0f).setDuration(300);

        removeListeners();
        unbinder.unbind();
        searchPresenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        releaseSearchComponent();
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

    // region SearchPresentationContract.View Methods

    @Override
    public void showEmptyView() {
        emptyLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void setEmptyText(String emptyText) {
        emptyTextView.setText(emptyText);
    }

    @Override
    public void showLoadingView() {
        searchProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        searchProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorView() {

        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.main_content),
                TrestleUtility.getFormattedText(getString(R.string.network_connection_unavailable), font, 16),
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.retry, view -> searchPresenter.onLoadSearch(searchQueryChangeObservable));
        View snackBarView = snackbar.getView();
//                            snackBarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey_200));
        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.secondary_text_light));
        textView.setTypeface(font);

        snackbar.show();
    }

    @Override
    public void addMoviesToAdapter(List<MovieDomainModel> movies) {
        searchMoviesAdapter.addAll(moviePresentationModelMapper.mapListToPresentationModelList(movies));
    }

    @Override
    public void clearMoviesAdapter() {
        searchMoviesAdapter.clear();
    }

    @Override
    public void hideMoviesView() {
        moviesLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMoviesView() {
        moviesLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void addTelevisionShowsToAdapter(List<TelevisionShowDomainModel> televisionShows) {
        searchTelevisionShowsAdapter.addAll(televisionShowPresentationModelMapper.mapListToPresentationModelList(televisionShows));
    }

    @Override
    public void clearTelevisionShowsAdapter() {
        searchTelevisionShowsAdapter.clear();
    }

    @Override
    public void hideTelevisionShowsView() {
        televisionShowsLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showTelevisionShowsView() {
        televisionShowsLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void addPersonsToAdapter(List<PersonDomainModel> persons) {
        searchPersonsAdapter.addAll(personPresentationModelMapper.mapListToPresentationModelList(persons));
    }

    @Override
    public void clearPersonsAdapter() {
        searchPersonsAdapter.clear();
    }

    @Override
    public void hidePersonsView() {
        personsLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showPersonsView() {
        personsLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void openMovieDetails(MoviePresentationModel movie) {
        Window window = getActivity().getWindow();
//                window.setStatusBarColor(statusBarColor);

        Pair<View, String> moviePair  = getMoviePair();
        ActivityOptionsCompat options = getActivityOptionsCompat(moviePair);

        window.setExitTransition(null);
        ActivityCompat.startActivity(getActivity(), MovieDetailsActivity.createIntent(getContext(), movie), options.toBundle());
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

    @Override
    public void openPersonDetails(PersonPresentationModel person) {
        Window window = getActivity().getWindow();
//            window.setStatusBarColor(primaryDark);

        Pair<View, String> personPair  = getPersonPair();
        ActivityOptionsCompat options = getActivityOptionsCompat(personPair);

        window.setExitTransition(null);
        ActivityCompat.startActivity(getActivity(), PersonDetailsActivity.createIntent(getContext(), person), options.toBundle());
    }

    // endregion

    // region Helper Methods
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

    private void removeListeners() {
        sharedElementEnterTransition.removeListener(enterTransitionTransitionListener);
//        sharedElementReturnTransition.removeListener(returnTransitionTransitionListener);
//        moviesAdapter.setOnItemClickListener(null);
    }

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

    private Pair<View, String> getMoviePair(){
        Resources resources = getResources();
        String transitionName = resources.getString(R.string.transition_movie_thumbnail);
        View view = selectedMovieView.findViewById(R.id.thumbnail_iv);
        return getPair(view, transitionName);
    }

    private Pair<View, String> getTelevisionShowPair(){
        Resources resources = getResources();
        String transitionName = resources.getString(R.string.transition_television_show_thumbnail);
        View view = selectedTelevisionShowView.findViewById(R.id.thumbnail_iv);
        return getPair(view, transitionName);
    }

    private Pair<View, String> getPersonPair(){
        Resources resources = getResources();
        String transitionName = resources.getString(R.string.transition_person_thumbnail);
        View view = selectedPersonView.findViewById(R.id.thumbnail_iv);
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

    private SearchComponent createSearchComponent(){
        searchComponent = ((MovieHubApplication)getActivity().getApplication())
                .getApplicationComponent()
                .createSubcomponent(new SearchModule(this));
        return searchComponent;
    }

    public void releaseSearchComponent(){
        searchComponent = null;
    }
    // endregion
}
