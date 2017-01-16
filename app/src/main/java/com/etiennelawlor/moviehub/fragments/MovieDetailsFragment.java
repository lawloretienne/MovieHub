package com.etiennelawlor.moviehub.fragments;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.activities.MovieDetailsActivity;
import com.etiennelawlor.moviehub.activities.PersonDetailsActivity;
import com.etiennelawlor.moviehub.adapters.BaseAdapter;
import com.etiennelawlor.moviehub.adapters.MovieCreditsAdapter;
import com.etiennelawlor.moviehub.adapters.SimilarMoviesAdapter;
import com.etiennelawlor.moviehub.models.FullMovie;
import com.etiennelawlor.moviehub.network.MovieHubService;
import com.etiennelawlor.moviehub.network.ServiceGenerator;
import com.etiennelawlor.moviehub.network.interceptors.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.network.models.Configuration;
import com.etiennelawlor.moviehub.network.models.Genre;
import com.etiennelawlor.moviehub.network.models.Images;
import com.etiennelawlor.moviehub.network.models.Movie;
import com.etiennelawlor.moviehub.network.models.MovieCredit;
import com.etiennelawlor.moviehub.network.models.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.MovieReleaseDate;
import com.etiennelawlor.moviehub.network.models.MovieReleaseDateEnvelope;
import com.etiennelawlor.moviehub.network.models.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.network.models.MoviesEnvelope;
import com.etiennelawlor.moviehub.network.models.Person;
import com.etiennelawlor.moviehub.prefs.MovieHubPrefs;
import com.etiennelawlor.moviehub.ui.GravitySnapHelper;
import com.etiennelawlor.moviehub.utilities.AnimationUtility;
import com.etiennelawlor.moviehub.utilities.ColorUtility;
import com.etiennelawlor.moviehub.utilities.DateUtility;
import com.etiennelawlor.moviehub.utilities.DisplayUtility;
import com.etiennelawlor.moviehub.utilities.FontCache;
import com.etiennelawlor.moviehub.utilities.NetworkUtility;
import com.etiennelawlor.moviehub.utilities.ViewUtility;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func4;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 12/18/16.
 */

public class MovieDetailsFragment extends BaseFragment {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    public static final String KEY_MOVIE = "KEY_MOVIE";
    public static final String KEY_PERSON = "KEY_PERSON";
    private static final float SCRIM_ADJUSTMENT = 0.075f;
    private static final int DELAY = 0;
    // endregion

    // region Views
    @BindView(R.id.main_content)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.backdrop_iv)
    ImageView backdropImageView;
    @BindView(R.id.backdrop_fl)
    FrameLayout backdropFrameLayout;
    @BindView(R.id.movie_poster_iv)
    ImageView moviePosterImageView;
    @BindView(R.id.title_tv)
    TextView titleTextView;
    @BindView(R.id.duration_tv)
    TextView durationTextView;
    @BindView(R.id.genres_tv)
    TextView genresTextView;
    @BindView(R.id.overview_tv)
    TextView overviewTextView;
    @BindView(R.id.status_tv)
    TextView statusTextView;
    @BindView(R.id.release_date_tv)
    TextView releaseDateTextView;
    @BindView(R.id.budget_tv)
    TextView budgetTextView;
    @BindView(R.id.revenue_tv)
    TextView revenueTextView;
    @BindView(R.id.rating_tv)
    TextView ratingTextView;
    @BindView(R.id.movie_details_header_ll)
    LinearLayout movieDetailsHeaderLinearLayout;
    @BindView(R.id.movie_details_body_ll)
    LinearLayout movieDetailsBodyLinearLayout;
    @BindView(R.id.nsv)
    NestedScrollView nestedScrollView;
    @BindView(R.id.cast_vs)
    ViewStub castViewStub;
    @BindView(R.id.crew_vs)
    ViewStub crewViewStub;
    @BindView(R.id.similar_movies_vs)
    ViewStub similarMoviesViewStub;
    // endregion

    // region Member Variables
    private Movie movie;
    private Unbinder unbinder;
    private Typeface font;
    private MovieHubService movieHubService;
    private CompositeSubscription compositeSubscription;
    private int moviePosterHeight;
    private int padding;
    private int scrollThreshold;
    private int statusBarColor;
    private SimilarMoviesAdapter similarMoviesAdapter;
    private MovieCreditsAdapter castAdapter;
    private MovieCreditsAdapter crewAdapter;
    private Transition sharedElementEnterTransition;
    private MovieCreditsEnvelope movieCreditsEnvelope;
    private MoviesEnvelope moviesEnvelope;
    private MovieReleaseDatesEnvelope movieReleaseDatesEnvelope;
    private final Handler handler = new Handler();
    // endregion

    // region Listeners
    private NestedScrollView.OnScrollChangeListener nestedScrollViewOnScrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            scrollThreshold = moviePosterHeight - movieDetailsHeaderLinearLayout.getMeasuredHeight() + padding;
            if (scrollY >= scrollThreshold) {
                String name = "";
                if (movie != null) {
                    name = movie.getTitle();
                }
                setCollapsingToolbarTitle(name);

            } else {
                setCollapsingToolbarTitle("");
            }
        }
    };

    private BaseAdapter.OnItemClickListener similarMoviesAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            Movie movie = similarMoviesAdapter.getItem(position);
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

    private Transition.TransitionListener transitionTransitionListener = new Transition.TransitionListener() {
        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {
            setUpFullMovieSubscription();
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

    private ViewTreeObserver.OnGlobalLayoutListener movieDetailsHeaderTreeObserverOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) nestedScrollView.getLayoutParams();
            AppBarLayout.ScrollingViewBehavior behavior =
                    (AppBarLayout.ScrollingViewBehavior) params.getBehavior();
            behavior.setOverlayTop(DisplayUtility.dp2px(getContext(), 156) - movieDetailsHeaderLinearLayout.getMeasuredHeight());

            movieDetailsHeaderLinearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    };

    private BaseAdapter.OnItemClickListener castAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            MovieCredit movieCredit = castAdapter.getItem(position);
            if(movieCredit != null){
                Person person = new Person();

                person.setName(movieCredit.getName());
                person.setId(movieCredit.getId());
                person.setProfilePath(movieCredit.getProfilePath());

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

    private BaseAdapter.OnItemClickListener crewAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            MovieCredit movieCredit = crewAdapter.getItem(position);
            if(movieCredit != null){
                Person person = new Person();

                person.setName(movieCredit.getName());
                person.setId(movieCredit.getId());
                person.setProfilePath(movieCredit.getProfilePath());

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
    // endregion

    // region Callbacks
    // endregion

    // region Constructors
    public MovieDetailsFragment() {
    }
    // endregion

    // region Factory Methods
    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    public static MovieDetailsFragment newInstance(Bundle extras) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().supportPostponeEnterTransition();

        compositeSubscription = new CompositeSubscription();

        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(getContext()));

        font = FontCache.getTypeface("Lato-Medium.ttf", getContext());

        moviePosterHeight = DisplayUtility.dp2px(getContext(), 156);
        padding = DisplayUtility.dp2px(getContext(), 16);

        if (getArguments() != null) {
            movie = getArguments().getParcelable(KEY_MOVIE);
        }

        setHasOptionsMenu(true);

        sharedElementEnterTransition = getActivity().getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(transitionTransitionListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
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
            setCollapsingToolbarTitle("");
        }

        if(movie != null){
            setUpBackdrop();
            setUpPoster();
            setUpTitle();

            movieDetailsHeaderLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(movieDetailsHeaderTreeObserverOnGlobalLayoutListener);
        }

        nestedScrollView.setOnScrollChangeListener(nestedScrollViewOnScrollChangeListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeSubscription.unsubscribe();
    }
    // endregion

    // region Helper Methods
    private void removeListeners() {
        sharedElementEnterTransition.removeListener(transitionTransitionListener);
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) null);

        if(castAdapter != null)
            castAdapter.setOnItemClickListener(null);

        if(crewAdapter != null)
            crewAdapter.setOnItemClickListener(null);

        if(similarMoviesAdapter != null)
            similarMoviesAdapter.setOnItemClickListener(null);
    }

    private void setUpBackdrop(){
        String backdropUrl = getBackdropUrl(movie);
        int height = DisplayUtility.dp2px(getContext(), 256);

        if (!TextUtils.isEmpty(backdropUrl)) {
            Picasso.with(backdropImageView.getContext())
                    .load(backdropUrl)
                    .resize((int)(1.5D*height), height)
                    .centerCrop()
                    .into(backdropImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            final Bitmap bitmap = ((BitmapDrawable) backdropImageView.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    boolean isDark;
                                    @ColorUtility.Lightness int lightness = ColorUtility.isDark(palette);
                                    if (lightness == ColorUtility.LIGHTNESS_UNKNOWN) {
                                        isDark = ColorUtility.isDark(bitmap, bitmap.getWidth() / 2, 0);
                                    } else {
                                        isDark = lightness == ColorUtility.IS_DARK;
                                    }

                                    if (!isDark && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        // Make back icon dark on light images
                                        ImageButton backButton = (ImageButton) toolbar.getChildAt(0);
                                        backButton.setColorFilter(ContextCompat.getColor(getContext(), R.color.dark_icon));

                                        // Make toolbar title text color dark
                                        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.eighty_percent_transparency_black));
                                    }

                                    // color the status bar. Set a complementary dark color on L,
                                    // light or dark color on M (with matching status bar icons)
                                    statusBarColor = getActivity().getWindow().getStatusBarColor();
                                    final Palette.Swatch topColor =
                                            ColorUtility.getMostPopulousSwatch(palette);
                                    if (topColor != null
                                            && (isDark || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
                                        statusBarColor = ColorUtility.scrimify(topColor.getRgb(),
                                                isDark, SCRIM_ADJUSTMENT);
                                        // set a light status bar on M+
                                        if (!isDark && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            ViewUtility.setLightStatusBar(getActivity().getWindow().getDecorView());
                                        }
                                    }

                                    if (statusBarColor != getActivity().getWindow().getStatusBarColor()) {
                                        ValueAnimator statusBarColorAnim = ValueAnimator.ofArgb(
                                                getActivity().getWindow().getStatusBarColor(), statusBarColor);
                                        statusBarColorAnim.addUpdateListener(new ValueAnimator
                                                .AnimatorUpdateListener() {
                                            @Override
                                            public void onAnimationUpdate(ValueAnimator animation) {
                                                if(getActivity() != null){
                                                    getActivity().getWindow().setStatusBarColor(
                                                            (int) animation.getAnimatedValue());
                                                }
                                            }
                                        });
                                        statusBarColorAnim.setDuration(500L);
                                        statusBarColorAnim.setInterpolator(
                                                AnimationUtility.getFastOutSlowInInterpolator(getContext()));
                                        statusBarColorAnim.start();
                                    }

                                    if (isDark || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        GradientDrawable gradientDrawable = new GradientDrawable(
                                                GradientDrawable.Orientation.BOTTOM_TOP,
                                                new int[] {
                                                        ContextCompat.getColor(getContext(), android.R.color.transparent),
                                                        statusBarColor});

                                        backdropFrameLayout.setForeground(gradientDrawable);
                                        collapsingToolbarLayout.setContentScrim(new ColorDrawable(ColorUtility.modifyAlpha(statusBarColor, 0.9f)));
                                    } else {
                                        GradientDrawable gradientDrawable = new GradientDrawable(
                                                GradientDrawable.Orientation.BOTTOM_TOP,
                                                new int[] {
                                                        ContextCompat.getColor(getContext(), android.R.color.transparent),
                                                        ContextCompat.getColor(getContext(), R.color.status_bar_color)});

                                        backdropFrameLayout.setForeground(gradientDrawable);
                                        collapsingToolbarLayout.setContentScrim(new ColorDrawable(ColorUtility.modifyAlpha(ContextCompat.getColor(getContext(), R.color.status_bar_color), 0.9f)));
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }
    }

    private void setUpPoster(){
        String posterUrl = getPosterUrl(movie);
        if (!TextUtils.isEmpty(posterUrl)) {
            Picasso.with(moviePosterImageView.getContext())
                    .load(posterUrl)
                    .resize(DisplayUtility.dp2px(moviePosterImageView.getContext(), 104), DisplayUtility.dp2px(moviePosterImageView.getContext(), 156))
                    .centerCrop()
                    .into(moviePosterImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            final Bitmap bitmap = ((BitmapDrawable) moviePosterImageView.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    setUpMovieHeaderBackgroundColor(palette);
                                    setUpTitleTextColor(titleTextView, palette);

                                    getActivity().supportStartPostponedEnterTransition();
                                }
                            });
                        }

                        @Override
                        public void onError() {
                            getActivity().supportStartPostponedEnterTransition();
                        }
                    });
        }
    }

    private String getPosterUrl(Movie movie){
        String posterUrl = "";
        Configuration configuration = MovieHubPrefs.getConfiguration(getContext());
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {

                List<String> posterSizes = images.getPosterSizes();
                if (posterSizes != null && posterSizes.size() > 0) {
                    String posterSize;
                    if (posterSizes.size() > 1) {
                        posterSize = posterSizes.get(posterSizes.size() - 2);
                    } else {
                        posterSize = posterSizes.get(posterSizes.size() - 1);
                    }

                    String secureBaseUrl = images.getSecureBaseUrl();
                    String posterPath = movie.getPosterPath();

                    posterUrl = String.format("%s%s%s", secureBaseUrl, posterSize, posterPath);
                }
            }
        }
        return posterUrl;
    }

    private String getBackdropUrl(Movie movie){
        String backdropUrl = "";

        Configuration configuration = MovieHubPrefs.getConfiguration(getContext());
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {

                List<String> backdropSizes = images.getBackdropSizes();
                if (backdropSizes != null && backdropSizes.size() > 0) {
                    String backdropSize;
                    if (backdropSizes.size() > 1) {
                        backdropSize = backdropSizes.get(backdropSizes.size() - 2);
                    } else {
                        backdropSize = backdropSizes.get(backdropSizes.size() - 1);
                    }

                    String secureBaseUrl = images.getSecureBaseUrl();
                    String backdropPath = movie.getBackdropPath();

                    backdropUrl = String.format("%s%s%s", secureBaseUrl, backdropSize, backdropPath);
                }
            }
        }

        return backdropUrl;
    }

    private void setUpFullMovieSubscription(){
        int movieId = movie.getId();
        final Palette posterPalette = movie.getPosterPalette();

        Subscription fullMovieSubscription = Observable.combineLatest(
                movieHubService.getMovieDetails(movieId),
                movieHubService.getMovieCredits(movieId),
                movieHubService.getSimilarMovies(movieId),
                movieHubService.getMovieReleaseDates(movieId),
                new Func4<Movie, MovieCreditsEnvelope, MoviesEnvelope, MovieReleaseDatesEnvelope, FullMovie>() {
                    @Override
                    public FullMovie call(Movie movie, MovieCreditsEnvelope movieCreditsEnvelope, MoviesEnvelope moviesEnvelope, MovieReleaseDatesEnvelope movieReleaseDatesEnvelope) {
                        return new FullMovie(movie, movieCreditsEnvelope, moviesEnvelope, movieReleaseDatesEnvelope);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FullMovie>() {
                    @Override
                    public void call(FullMovie fullMovie) {
                        if (fullMovie != null) {
                            movie = fullMovie.getMovie();
                            movie.setPosterPalette(posterPalette);
                            movieCreditsEnvelope = fullMovie.getMovieCreditsEnvelope();
                            moviesEnvelope = fullMovie.getMoviesEnvelope();
                            movieReleaseDatesEnvelope = fullMovie.getMovieReleaseDatesEnvelope();

                            setUpBackdrop();
                            setUpOverview();
                            setUpDuration();
                            setUpGenres();
                            setUpStatus();
                            setUpReleaseDate();
                            setUpBudget();
                            setUpRevenue();
                            setUpRating();

                            showMovieDetailsBody();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable t) {

                        t.printStackTrace();
                        if (NetworkUtility.isKnownException(t)) {
//                            errorTextView.setText("Can't load data.\nCheck your network connection.");
//                            errorLinearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });

        compositeSubscription.add(fullMovieSubscription);
    }

    private void showMovieDetailsBody(){
        final int targetHeight = AnimationUtility.getTargetHeight(movieDetailsBodyLinearLayout);
        Animation animation = AnimationUtility.getExpandHeightAnimation(movieDetailsBodyLinearLayout, targetHeight);
        // 1dp/ms
        animation.setDuration((int)(targetHeight / movieDetailsBodyLinearLayout.getContext().getResources().getDisplayMetrics().density));
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setUpCast(movieCreditsEnvelope);
                        setUpCrew(movieCreditsEnvelope);
                        setUpSimilarMovies(moviesEnvelope);
                    }
                }, DELAY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        movieDetailsBodyLinearLayout.startAnimation(animation);
    }

    private void setUpCast(MovieCreditsEnvelope movieCreditsEnvelope){
        if(movieCreditsEnvelope != null){
            List<MovieCredit> cast = movieCreditsEnvelope.getCast();
            if(cast != null && cast.size()>0){
                View castView = castViewStub.inflate();

                RecyclerView castRecyclerView = ButterKnife.findById(castView, R.id.cast_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                castRecyclerView.setLayoutManager(layoutManager);
                castAdapter = new MovieCreditsAdapter(getContext());
                castAdapter.setOnItemClickListener(castAdapterOnItemClickListener);
                castRecyclerView.setAdapter(castAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(castRecyclerView);

                castAdapter.addAll(cast);
            }
        }
    }

    private void setUpCrew(MovieCreditsEnvelope movieCreditsEnvelope){
        if(movieCreditsEnvelope != null){
            List<MovieCredit> crew = movieCreditsEnvelope.getCrew();
            if(crew != null && crew.size()>0){
                View crewView = crewViewStub.inflate();

                RecyclerView crewRecyclerView = ButterKnife.findById(crewView, R.id.crew_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                crewRecyclerView.setLayoutManager(layoutManager);
                crewAdapter = new MovieCreditsAdapter(getContext());
                crewAdapter.setOnItemClickListener(crewAdapterOnItemClickListener);
                crewRecyclerView.setAdapter(crewAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(crewRecyclerView);

                crewAdapter.addAll(crew);
            }
        }
    }

    private void setUpSimilarMovies(MoviesEnvelope moviesEnvelope){
        if(moviesEnvelope != null){
            List<Movie> similarMovies = moviesEnvelope.getMovies();
            if(similarMovies != null && similarMovies.size()>0){
                View similarMoviesView = similarMoviesViewStub.inflate();

                RecyclerView similarMoviesRecyclerView = ButterKnife.findById(similarMoviesView, R.id.similar_movies_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                similarMoviesRecyclerView.setLayoutManager(layoutManager);
                similarMoviesAdapter = new SimilarMoviesAdapter(getContext());
                similarMoviesAdapter.setOnItemClickListener(similarMoviesAdapterOnItemClickListener);
                similarMoviesRecyclerView.setAdapter(similarMoviesAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(similarMoviesRecyclerView);

                Collections.sort(similarMovies, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        int year1 = -1;
                        if(m1.getReleaseDateYear() != -1){
                            year1 = m1.getReleaseDateYear();
                        }

                        int year2 = -1;
                        if(m2.getReleaseDateYear() != -1){
                            year2 = m2.getReleaseDateYear();
                        }

                        if(year1 > year2)
                            return -1;
                        else if(year1 < year2)
                            return 1;
                        else
                            return 0;
                    }
                });

                similarMoviesAdapter.addAll(similarMovies);
            }
        }
    }

    private void setUpMovieHeaderBackgroundColor(Palette palette){
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(getContext(), R.color.grey_600);
            int endColor = swatch.getRgb();

            AnimationUtility.animateBackgroundColorChange(movieDetailsHeaderLinearLayout, startColor, endColor);
        }
    }

    private void setUpTitleTextColor(final TextView tv, Palette palette){
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(tv.getContext(), R.color.primary_text_light);
            int endColor = swatch.getTitleTextColor();

            AnimationUtility.animateTextColorChange(tv, startColor, endColor);
        }
    }

    private void setUpTitle(){
        String title = movie.getTitle();
        String releaseDate = movie.getReleaseDate();
        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(releaseDate)) {
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);
            titleTextView.setText(String.format("%s (%s)", title, String.format("%d", calendar.get(Calendar.YEAR))));
        }
    }

    private void setUpOverview(){
        String overview = movie.getOverview();
        if(!TextUtils.isEmpty(overview)){
            overviewTextView.setText(overview);
        } else {
            overviewTextView.setText("N/A");
        }
    }

    private void setUpDuration(){
        int runTime = movie.getRuntime();
        if(runTime>0) {
            int hours = runTime/60;
            int minutes = runTime%60;
            if(hours>0){
                if(minutes>0){
                    durationTextView.setText(String.format("%dh %dm", hours, minutes));
                } else {
                    durationTextView.setText(String.format("%dh", hours));
                }
            } else {
                durationTextView.setText(String.format("%dm", minutes));
            }
        } else {
            durationTextView.setText("N/A");
        }
    }

    private void setUpGenres(){
        List<Genre> genres = movie.getGenres();
        if(genres != null && genres.size()>0){
            StringBuilder stringBuilder = new StringBuilder("");

            for(int i=0; i<genres.size(); i++){
                Genre genre = genres.get(i);
                stringBuilder.append(genre.getName());
                if(i!=genres.size()-1){
                    stringBuilder.append(" | ");
                }
            }

            genresTextView.setText(stringBuilder);
        }
    }

    private void setUpStatus(){
        String status = movie.getStatus();
        if(!TextUtils.isEmpty(status)){
            statusTextView.setText(status);
        }
    }

    private void setUpReleaseDate(){
        String releaseDate = movie.getReleaseDate();
        if(!TextUtils.isEmpty(releaseDate)){
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);

            String month = DateUtility.getMonth(calendar.get(Calendar.MONTH));
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);

            String formattedReleaseDate = String.format("%s %d, %d", month, day, year);

            releaseDateTextView.setText(formattedReleaseDate);
        }
    }

    private void setUpBudget(){
        int budget = movie.getBudget();
        if(budget > 0){
            budgetTextView.setText(String.format("$%s", NumberFormat.getNumberInstance(Locale.US).format(budget)));
        } else {
            budgetTextView.setText("N/A");
        }
    }

    private void setUpRevenue(){
        long revenue = movie.getRevenue();
        if(revenue > 0L){
            revenueTextView.setText(String.format("$%s", NumberFormat.getNumberInstance(Locale.US).format(revenue)));
        } else {
            revenueTextView.setText("N/A");
        }
    }

    private void setUpRating(){
        String rating = "";
        List<MovieReleaseDateEnvelope> movieReleaseDateEnvelopes = movieReleaseDatesEnvelope.getMovieReleaseDateEnvelopes();
        if(movieReleaseDateEnvelopes != null && movieReleaseDateEnvelopes.size()>0){
            for(MovieReleaseDateEnvelope movieReleaseDateEnvelope : movieReleaseDateEnvelopes){
                if(movieReleaseDateEnvelope != null){
                    String iso31661 = movieReleaseDateEnvelope.getIso31661();
                    if(iso31661.equals("US")){
                        List<MovieReleaseDate> movieReleaseDates = movieReleaseDateEnvelope.getMovieReleaseDates();
                        if(movieReleaseDates != null && movieReleaseDates.size()>0){
                            for(MovieReleaseDate movieReleaseDate : movieReleaseDates){
                                if(!TextUtils.isEmpty(movieReleaseDate.getCertification())){
                                    rating = movieReleaseDate.getCertification();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if(!TextUtils.isEmpty(rating)){
            ratingTextView.setText(rating);
        } else {
            ratingTextView.setVisibility(View.GONE);
        }
    }

    private void setCollapsingToolbarTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            collapsingToolbarLayout.setCollapsedTitleTypeface(font);
            collapsingToolbarLayout.setTitle(title);
        } else {
            collapsingToolbarLayout.setCollapsedTitleTypeface(font);
            collapsingToolbarLayout.setTitle("");
        }
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    private ActivityOptionsCompat getActivityOptionsCompat(Pair pair){
        ActivityOptionsCompat options = null;

        Pair<View, String> navigationBarPair  = getNavigationBarPair();
        Pair<View, String> statusBarPair = getStatusBarPair();

        if(pair!=null && statusBarPair!= null && navigationBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, statusBarPair, navigationBarPair);
        } else if(pair != null && statusBarPair != null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, statusBarPair);
        } else if(pair != null && navigationBarPair != null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    pair, navigationBarPair);
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

    // endregion
}
