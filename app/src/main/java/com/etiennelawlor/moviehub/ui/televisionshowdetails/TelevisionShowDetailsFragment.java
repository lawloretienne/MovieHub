package com.etiennelawlor.moviehub.ui.televisionshowdetails;

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
import com.etiennelawlor.moviehub.data.local.sharedpreferences.PreferencesHelper;
import com.etiennelawlor.moviehub.data.model.FullTelevisionShow;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.ContentRating;
import com.etiennelawlor.moviehub.data.remote.response.Genre;
import com.etiennelawlor.moviehub.data.remote.response.Images;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowCredit;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.ui.base.BaseAdapter;
import com.etiennelawlor.moviehub.ui.base.BaseFragment;
import com.etiennelawlor.moviehub.ui.common.GravitySnapHelper;
import com.etiennelawlor.moviehub.ui.persondetails.PersonDetailsActivity;
import com.etiennelawlor.moviehub.util.AnimationUtility;
import com.etiennelawlor.moviehub.util.ColorUtility;
import com.etiennelawlor.moviehub.util.DateUtility;
import com.etiennelawlor.moviehub.util.DisplayUtility;
import com.etiennelawlor.moviehub.util.FontCache;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.ViewUtility;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class TelevisionShowDetailsFragment extends BaseFragment implements TelevisionShowDetailsContract.View {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    public static final String KEY_TELEVISION_SHOW = "KEY_TELEVISION_SHOW";
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
    @BindView(R.id.television_show_poster_iv)
    ImageView televisionShowPosterImageView;
    @BindView(R.id.title_tv)
    TextView titleTextView;
    @BindView(R.id.seasons_tv)
    TextView seasonsTextView;
    @BindView(R.id.genres_tv)
    TextView genresTextView;
    @BindView(R.id.overview_tv)
    TextView overviewTextView;
    @BindView(R.id.status_tv)
    TextView statusTextView;
    @BindView(R.id.first_air_date_tv)
    TextView firstAirDateTextView;
    @BindView(R.id.networks_tv)
    TextView networksTextView;
    @BindView(R.id.rating_tv)
    TextView ratingTextView;
    @BindView(R.id.television_show_details_header_ll)
    LinearLayout televisionShowDetailsHeaderLinearLayout;
    @BindView(R.id.television_show_details_body_ll)
    LinearLayout televisionShowDetailsBodyLinearLayout;
    @BindView(R.id.nsv)
    NestedScrollView nestedScrollView;
    @BindView(R.id.cast_vs)
    ViewStub castViewStub;
    @BindView(R.id.crew_vs)
    ViewStub crewViewStub;
    @BindView(R.id.similar_television_shows_vs)
    ViewStub similarTelevisionShowsViewStub;
    // endregion

    // region Member Variables
    private TelevisionShow televisionShow;
    private Unbinder unbinder;
    private Typeface font;
    private MovieHubService movieHubService;
    private CompositeSubscription compositeSubscription;
    private int televisionShowPosterHeight;
    private int padding;
    private int scrollThreshold;
    private int statusBarColor;
    private SimilarTelevisionShowsAdapter similarTelevisionShowsAdapter;
    private TelevisionShowCreditsAdapter castAdapter;
    private TelevisionShowCreditsAdapter crewAdapter;
    private Transition sharedElementEnterTransition;
    private TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope;
    private TelevisionShowsEnvelope televisionShowsEnvelope;
    private TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope;
    private final Handler handler = new Handler();
    // endregion

    // region Listeners
    private NestedScrollView.OnScrollChangeListener nestedScrollViewOnScrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            scrollThreshold = televisionShowPosterHeight - televisionShowDetailsHeaderLinearLayout.getMeasuredHeight() + padding;
            if (scrollY >= scrollThreshold) {
                String name = "";
                if (televisionShow != null) {
                    name = televisionShow.getName();
                }
                setCollapsingToolbarTitle(name);

            } else {
                setCollapsingToolbarTitle("");
            }
        }
    };

    private BaseAdapter.OnItemClickListener castAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            TelevisionShowCredit televisionShowCredit = castAdapter.getItem(position);
            if(televisionShowCredit != null){
                Person person = new Person();

                person.setName(televisionShowCredit.getName());
                person.setId(televisionShowCredit.getId());
                person.setProfilePath(televisionShowCredit.getProfilePath());

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
            TelevisionShowCredit televisionShowCredit = crewAdapter.getItem(position);
            if(televisionShowCredit != null){
                Person person = new Person();

                person.setName(televisionShowCredit.getName());
                person.setId(televisionShowCredit.getId());
                person.setProfilePath(televisionShowCredit.getProfilePath());

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

    private BaseAdapter.OnItemClickListener similarTelevisionShowsAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            TelevisionShow televisionShow = similarTelevisionShowsAdapter.getItem(position);
            if(televisionShow != null){
                Intent intent = new Intent(getActivity(), TelevisionShowDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_TELEVISION_SHOW, televisionShow);
                intent.putExtras(bundle);

                Window window = getActivity().getWindow();
                window.setStatusBarColor(statusBarColor);

                Resources resources = view.getResources();
                Pair<View, String> televisionShowPair  = getPair(view, resources.getString(R.string.transition_television_show_thumbnail));

                ActivityOptionsCompat options = getActivityOptionsCompat(televisionShowPair);

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
            setUpFullTelevisionShowSubscription();
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

    private ViewTreeObserver.OnGlobalLayoutListener televisionShowDetailsHeaderTreeObserverOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) nestedScrollView.getLayoutParams();
            AppBarLayout.ScrollingViewBehavior behavior =
                    (AppBarLayout.ScrollingViewBehavior) params.getBehavior();
            behavior.setOverlayTop(DisplayUtility.dp2px(getContext(), 156) - televisionShowDetailsHeaderLinearLayout.getMeasuredHeight());

            televisionShowDetailsHeaderLinearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    };
    // endregion

    // region Callbacks
    // endregion

    // region Constructors
    public TelevisionShowDetailsFragment() {
    }
    // endregion

    // region Factory Methods
    public static TelevisionShowDetailsFragment newInstance() {
        return new TelevisionShowDetailsFragment();
    }

    public static TelevisionShowDetailsFragment newInstance(Bundle extras) {
        TelevisionShowDetailsFragment fragment = new TelevisionShowDetailsFragment();
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

        televisionShowPosterHeight = DisplayUtility.dp2px(getContext(), 156);
        padding = DisplayUtility.dp2px(getContext(), 16);

        if (getArguments() != null) {
            televisionShow = getArguments().getParcelable(KEY_TELEVISION_SHOW);
        }

        setHasOptionsMenu(true);

        sharedElementEnterTransition = getActivity().getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(transitionTransitionListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_television_show_details, container, false);
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

        if(televisionShow != null){
            setUpBackdrop();
            setUpPoster();
            setUpTitle();

            televisionShowDetailsHeaderLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(televisionShowDetailsHeaderTreeObserverOnGlobalLayoutListener);
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

        if(similarTelevisionShowsAdapter != null)
            similarTelevisionShowsAdapter.setOnItemClickListener(null);
    }

    private void setUpBackdrop(){
        String backdropUrl = getBackdropUrl(televisionShow);
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
                                    if (topColor != null &&
                                            (isDark || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
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
        String posterUrl = getPosterUrl(televisionShow);
        if (!TextUtils.isEmpty(posterUrl)) {
            Picasso.with(televisionShowPosterImageView.getContext())
                    .load(posterUrl)
                    .resize(DisplayUtility.dp2px(televisionShowPosterImageView.getContext(), 104), DisplayUtility.dp2px(televisionShowPosterImageView.getContext(), 156))
                    .centerCrop()
                    .into(televisionShowPosterImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            final Bitmap bitmap = ((BitmapDrawable) televisionShowPosterImageView.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    setUpTelevisionShowHeaderBackgroundColor(palette);
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

    private String getPosterUrl(TelevisionShow televisionShow){
        String posterUrl = "";
        Configuration configuration = PreferencesHelper.getConfiguration(getContext());
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
                    String posterPath = televisionShow.getPosterPath();

                    posterUrl = String.format("%s%s%s", secureBaseUrl, posterSize, posterPath);
                }
            }
        }
        return posterUrl;
    }

    private String getBackdropUrl(TelevisionShow televisionShow){
        String backdropUrl = "";

        Configuration configuration = PreferencesHelper.getConfiguration(getContext());
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
                    String backdropPath = televisionShow.getBackdropPath();

                    backdropUrl = String.format("%s%s%s", secureBaseUrl, backdropSize, backdropPath);
                }
            }
        }

        return backdropUrl;
    }

    private void setUpFullTelevisionShowSubscription(){
        int tvId = televisionShow.getId();
        final Palette posterPalette = televisionShow.getPosterPalette();

        Subscription fullTelevisionShowSubscription = Observable.combineLatest(
                movieHubService.getTelevisionShowDetails(tvId),
                movieHubService.getTelevisionShowCredits(tvId),
                movieHubService.getSimilarTelevisionShows(tvId),
                movieHubService.getTelevisionShowContentRatings(tvId),
                new Func4<TelevisionShow, TelevisionShowCreditsEnvelope, TelevisionShowsEnvelope, TelevisionShowContentRatingsEnvelope, FullTelevisionShow>() {
                    @Override
                    public FullTelevisionShow call(TelevisionShow televisionShow, TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope, TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope) {
                        return new FullTelevisionShow(televisionShow, televisionShowCreditsEnvelope, televisionShowsEnvelope, televisionShowContentRatingsEnvelope);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FullTelevisionShow>() {
                    @Override
                    public void call(FullTelevisionShow fullTelevisionShow) {
                        if (fullTelevisionShow != null) {
                            televisionShow = fullTelevisionShow.getTelevisionShow();
                            televisionShow.setPosterPalette(posterPalette);
                            televisionShowCreditsEnvelope = fullTelevisionShow.getTelevisionShowCreditsEnvelope();
                            televisionShowsEnvelope = fullTelevisionShow.getTelevisionShowsEnvelope();
                            televisionShowContentRatingsEnvelope = fullTelevisionShow.getTelevisionShowContentRatingsEnvelope();

                            setUpBackdrop();
                            setUpOverview();
                            setUpGenres();
                            setUpSeasons();
                            setUpStatus();
                            setUpFirstAirDate();
                            setUpNetwork();
                            setUpRating();

                            showTelevisionShowDetailsBody();
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

        compositeSubscription.add(fullTelevisionShowSubscription);
    }

    private void showTelevisionShowDetailsBody(){
        final int targetHeight = AnimationUtility.getTargetHeight(televisionShowDetailsBodyLinearLayout);
        Animation animation = AnimationUtility.getExpandHeightAnimation(televisionShowDetailsBodyLinearLayout, targetHeight);
        // 1dp/ms
        animation.setDuration((int)(targetHeight / televisionShowDetailsBodyLinearLayout.getContext().getResources().getDisplayMetrics().density));
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setUpCast(televisionShowCreditsEnvelope);
                        setUpCrew(televisionShowCreditsEnvelope);
                        setUpSimilarTelevisionShows(televisionShowsEnvelope);
                    }
                }, DELAY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        televisionShowDetailsBodyLinearLayout.startAnimation(animation);
    }

    private void setUpCast(TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope){
        if(televisionShowCreditsEnvelope != null){
            List<TelevisionShowCredit> cast = televisionShowCreditsEnvelope.getCast();
            if(cast != null && cast.size()>0){
                View castView = castViewStub.inflate();

                RecyclerView castRecyclerView = ButterKnife.findById(castView, R.id.cast_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                castRecyclerView.setLayoutManager(layoutManager);
                castAdapter = new TelevisionShowCreditsAdapter(getContext());
                castAdapter.setOnItemClickListener(castAdapterOnItemClickListener);
                castRecyclerView.setAdapter(castAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(castRecyclerView);

                castAdapter.addAll(cast);
            }
        }
    }

    private void setUpCrew(TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope){
        if(televisionShowCreditsEnvelope != null){
            List<TelevisionShowCredit> crew = televisionShowCreditsEnvelope.getCrew();
            if(crew != null && crew.size()>0){
                View crewView = crewViewStub.inflate();

                RecyclerView crewRecyclerView = ButterKnife.findById(crewView, R.id.crew_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                crewRecyclerView.setLayoutManager(layoutManager);
                crewAdapter = new TelevisionShowCreditsAdapter(getContext());
                crewAdapter.setOnItemClickListener(crewAdapterOnItemClickListener);
                crewRecyclerView.setAdapter(crewAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(crewRecyclerView);

                crewAdapter.addAll(crew);
            }
        }
    }

    private void setUpSimilarTelevisionShows(TelevisionShowsEnvelope televisionShowsEnvelope){
        if(televisionShowsEnvelope != null){
            List<TelevisionShow> similarTelevisionShows = televisionShowsEnvelope.getTelevisionShows();
            if(similarTelevisionShows != null && similarTelevisionShows.size()>0){
                View similarTelevisionShowsView = similarTelevisionShowsViewStub.inflate();

                RecyclerView similarTelevisionShowsRecyclerView = ButterKnife.findById(similarTelevisionShowsView, R.id.similar_television_shows_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                similarTelevisionShowsRecyclerView.setLayoutManager(layoutManager);
                similarTelevisionShowsAdapter = new SimilarTelevisionShowsAdapter(getContext());
                similarTelevisionShowsAdapter.setOnItemClickListener(similarTelevisionShowsAdapterOnItemClickListener);
                similarTelevisionShowsRecyclerView.setAdapter(similarTelevisionShowsAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(similarTelevisionShowsRecyclerView);

                Collections.sort(similarTelevisionShows, new Comparator<TelevisionShow>() {
                    @Override
                    public int compare(TelevisionShow t1, TelevisionShow t2) {
                        int year1 = -1;
                        if(t1.getFirstAirDateYear() != -1){
                            year1 = t1.getFirstAirDateYear();
                        }

                        int year2 = -1;
                        if(t2.getFirstAirDateYear() != -1){
                            year2 = t2.getFirstAirDateYear();
                        }

                        if(year1 > year2)
                            return -1;
                        else if(year1 < year2)
                            return 1;
                        else
                            return 0;
                    }
                });

                similarTelevisionShowsAdapter.addAll(similarTelevisionShows);
            }
        }
    }

    private void setUpTelevisionShowHeaderBackgroundColor(Palette palette){
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(getContext(), R.color.grey_600);
            int endColor = swatch.getRgb();

            AnimationUtility.animateBackgroundColorChange(televisionShowDetailsHeaderLinearLayout, startColor, endColor);
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
        String name = televisionShow.getName();
        String firstAirDate = televisionShow.getFirstAirDate();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(firstAirDate)) {
            Calendar calendar = DateUtility.getCalendar(firstAirDate, PATTERN);
            titleTextView.setText(String.format("%s (%s)", name, String.format("%d", calendar.get(Calendar.YEAR))));
        }
    }

    private void setUpOverview(){
        String overview = televisionShow.getOverview();
        if(!TextUtils.isEmpty(overview)){
            overview = overview.trim();
            overviewTextView.setText(overview);
        } else {
            overviewTextView.setText("N/A");
        }
    }

    private void setUpSeasons(){
        int numberOfSeasons = televisionShow.getNumberOfSeasons();
        seasonsTextView.setText(String.format("%d", numberOfSeasons));
    }

    private void setUpGenres(){
        List<Genre> genres = televisionShow.getGenres();
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
        String status = televisionShow.getStatus();
        if(!TextUtils.isEmpty(status)){
            statusTextView.setText(status);
        }
    }

    private void setUpFirstAirDate(){
        String releaseDate = televisionShow.getFirstAirDate();
        if(!TextUtils.isEmpty(releaseDate)){
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);

            String month = DateUtility.getMonth(calendar.get(Calendar.MONTH));
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);

            String formattedReleaseDate = String.format("%s %d, %d", month, day, year);

            firstAirDateTextView.setText(formattedReleaseDate);
        }
    }

    private void setUpNetwork(){
        String network = televisionShow.getFormattedNetwork();
        if(!TextUtils.isEmpty(network)){
            networksTextView.setText(network);
        }
    }

    private void setUpRating(){
        String rating = "";
        List<ContentRating> contentRatings = televisionShowContentRatingsEnvelope.getContentRatings();
        if(contentRatings != null && contentRatings.size() > 0){
            for(ContentRating contentRating : contentRatings){
                String iso31661 = contentRating.getIso31661();
                if(iso31661.equals("US")){
                    rating = contentRating.getRating();
                    break;
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
