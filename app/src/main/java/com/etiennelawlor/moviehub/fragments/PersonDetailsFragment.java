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
import com.etiennelawlor.moviehub.activities.TelevisionShowDetailsActivity;
import com.etiennelawlor.moviehub.adapters.BaseAdapter;
import com.etiennelawlor.moviehub.adapters.PersonCreditsAdapter;
import com.etiennelawlor.moviehub.adapters.itemdecorations.HorizontalLinearItemDecoration;
import com.etiennelawlor.moviehub.models.FullPerson;
import com.etiennelawlor.moviehub.network.MovieHubService;
import com.etiennelawlor.moviehub.network.ServiceGenerator;
import com.etiennelawlor.moviehub.network.interceptors.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.network.models.Configuration;
import com.etiennelawlor.moviehub.network.models.Images;
import com.etiennelawlor.moviehub.network.models.Movie;
import com.etiennelawlor.moviehub.network.models.Person;
import com.etiennelawlor.moviehub.network.models.PersonCredit;
import com.etiennelawlor.moviehub.network.models.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.ProfileImage;
import com.etiennelawlor.moviehub.network.models.ProfileImages;
import com.etiennelawlor.moviehub.network.models.TelevisionShow;
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

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 12/18/16.
 */

public class PersonDetailsFragment extends BaseFragment {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    public static final String KEY_PERSON = "KEY_PERSON";
    public static final String KEY_MOVIE = "KEY_MOVIE";
    public static final String KEY_TELEVISION_SHOW = "KEY_TELEVISION_SHOW";
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
    @BindView(R.id.person_profile_iv)
    ImageView personProfileImageView;
    @BindView(R.id.title_tv)
    TextView titleTextView;
    @BindView(R.id.bio_tv)
    TextView bioTextView;
    @BindView(R.id.birthplace_tv)
    TextView birthplaceTextView;
    @BindView(R.id.dob_tv)
    TextView dateOfBirthTextView;
    @BindView(R.id.dod_tv)
    TextView dateOfDeathTextView;
    @BindView(R.id.dod_ll)
    LinearLayout dateOfDeathLinearLayout;
    @BindView(R.id.person_details_header_ll)
    LinearLayout personDetailsHeaderLinearLayout;
    @BindView(R.id.person_details_body_ll)
    LinearLayout personDetailsBodyLinearLayout;
    @BindView(R.id.nsv)
    NestedScrollView nestedScrollView;
    @BindView(R.id.cast_vs)
    ViewStub castViewStub;
    @BindView(R.id.crew_vs)
    ViewStub crewViewStub;
    // endregion

    // region Member Variables
    private Person person;
    private Unbinder unbinder;
    private Typeface font;
    private MovieHubService movieHubService;
    private CompositeSubscription compositeSubscription;
    private int personPosterHeight;
    private int padding;
    private int scrollThreshold;
    private int statusBarColor;
    private PersonCreditsAdapter castAdapter;
    private PersonCreditsAdapter crewAdapter;
    private Transition sharedElementEnterTransition;
    private PersonCreditsEnvelope personCreditsEnvelope;
    private final Handler handler = new Handler();
    // endregion

    // region Listeners
    private NestedScrollView.OnScrollChangeListener nestedScrollViewOnScrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            scrollThreshold = personPosterHeight - personDetailsHeaderLinearLayout.getMeasuredHeight() + padding;
            if (scrollY >= scrollThreshold) {
                String name = "";
                if (person != null) {
                    name = person.getName();
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
            PersonCredit personCredit = castAdapter.getItem(position);
            if(personCredit != null){
                Intent intent;
                ActivityOptionsCompat options;
                Bundle bundle = new Bundle();
                Window window = getActivity().getWindow();
                Resources resources = view.getResources();

                String mediaType = personCredit.getMediaType();
                switch (mediaType){
                    case "movie":
                        Movie movie = new Movie();

                        movie.setTitle(personCredit.getTitle());
                        movie.setId(personCredit.getId());
                        movie.setPosterPath(personCredit.getPosterPath());
                        movie.setReleaseDate(personCredit.getReleaseDate());

                        intent = new Intent(getActivity(), MovieDetailsActivity.class);
                        bundle.putParcelable(KEY_MOVIE, movie);
                        intent.putExtras(bundle);

//            window.setStatusBarColor(primaryDark);

                        Pair<View, String> moviePair = getPair(view, resources.getString(R.string.transition_movie_thumbnail));

                        options = getActivityOptionsCompat(moviePair);

                        window.setExitTransition(null);
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                        break;
                    case "tv":
                        TelevisionShow televisionShow = new TelevisionShow();

                        televisionShow.setName(personCredit.getName());
                        televisionShow.setId(personCredit.getId());
                        televisionShow.setPosterPath(personCredit.getPosterPath());
                        televisionShow.setFirstAirDate(personCredit.getFirstAirDate());

                        intent = new Intent(getActivity(), TelevisionShowDetailsActivity.class);
                        bundle.putParcelable(KEY_TELEVISION_SHOW, televisionShow);
                        intent.putExtras(bundle);

//            window.setStatusBarColor(primaryDark);

                        Pair<View, String> televisionShowPair = getPair(view, resources.getString(R.string.transition_television_show_thumbnail));

                        options = getActivityOptionsCompat(televisionShowPair);

                        window.setExitTransition(null);
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                        break;
                    default:
                        break;
                }
            }
        }
    };

    private BaseAdapter.OnItemClickListener crewAdapterOnItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            PersonCredit personCredit = crewAdapter.getItem(position);
            if(personCredit != null){
                Intent intent;
                ActivityOptionsCompat options;
                Bundle bundle = new Bundle();
                Window window = getActivity().getWindow();
                Resources resources = view.getResources();

                String mediaType = personCredit.getMediaType();
                switch (mediaType){
                    case "movie":
                        Movie movie = new Movie();

                        movie.setTitle(personCredit.getTitle());
                        movie.setId(personCredit.getId());
                        movie.setPosterPath(personCredit.getPosterPath());
                        movie.setReleaseDate(personCredit.getReleaseDate());

                        intent = new Intent(getActivity(), MovieDetailsActivity.class);
                        bundle.putParcelable(KEY_MOVIE, movie);
                        intent.putExtras(bundle);

//            window.setStatusBarColor(primaryDark);

                        Pair<View, String> moviePair  = getPair(view, resources.getString(R.string.transition_movie_thumbnail));

                        options = getActivityOptionsCompat(moviePair);

                        window.setExitTransition(null);
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

                        break;
                    case "tv":
                        TelevisionShow televisionShow = new TelevisionShow();

                        televisionShow.setName(personCredit.getName());
                        televisionShow.setId(personCredit.getId());
                        televisionShow.setPosterPath(personCredit.getPosterPath());
                        televisionShow.setFirstAirDate(personCredit.getFirstAirDate());

                        intent = new Intent(getActivity(), TelevisionShowDetailsActivity.class);
                        bundle.putParcelable(KEY_TELEVISION_SHOW, televisionShow);
                        intent.putExtras(bundle);

//            window.setStatusBarColor(primaryDark);

                        Pair<View, String> televisionShowPair  = getPair(view, resources.getString(R.string.transition_television_show_thumbnail));

                        options = getActivityOptionsCompat(televisionShowPair);

                        window.setExitTransition(null);
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                        break;
                    default:
                        break;
                }
            }
        }
    };

    private Transition.TransitionListener transitionTransitionListener = new Transition.TransitionListener() {
        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {
            setUpFullPersonSubscription();
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

    private ViewTreeObserver.OnGlobalLayoutListener personDetailsHeaderTreeObserverOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) nestedScrollView.getLayoutParams();
            AppBarLayout.ScrollingViewBehavior behavior =
                    (AppBarLayout.ScrollingViewBehavior) params.getBehavior();
            behavior.setOverlayTop(DisplayUtility.dp2px(getContext(), 156) - personDetailsHeaderLinearLayout.getMeasuredHeight());

            personDetailsHeaderLinearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    };
    // endregion

    // region Callbacks
    // endregion

    // region Constructors
    public PersonDetailsFragment() {
    }
    // endregion

    // region Factory Methods
    public static PersonDetailsFragment newInstance() {
        return new PersonDetailsFragment();
    }

    public static PersonDetailsFragment newInstance(Bundle extras) {
        PersonDetailsFragment fragment = new PersonDetailsFragment();
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

        personPosterHeight = DisplayUtility.dp2px(getContext(), 156);
        padding = DisplayUtility.dp2px(getContext(), 16);

        if (getArguments() != null) {
            person = getArguments().getParcelable(KEY_PERSON);
        }

        setHasOptionsMenu(true);

        sharedElementEnterTransition = getActivity().getWindow().getSharedElementEnterTransition();
        sharedElementEnterTransition.addListener(transitionTransitionListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_person_details, container, false);
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

        if(person != null){
            setUpProfile();
            setUpTitle();

            personDetailsHeaderLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(personDetailsHeaderTreeObserverOnGlobalLayoutListener);
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
    }

    private void setUpBackdrop(){
        String backdropUrl = getBackdropUrl(person);
        if (!TextUtils.isEmpty(backdropUrl)) {
            int screenWidth = DisplayUtility.getScreenWidth(getContext());

            Picasso.with(backdropImageView.getContext())
                    .load(backdropUrl)
                    .resize(screenWidth, (int)(1.5D*screenWidth))
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

    private void setUpProfile(){
        String posterUrl = getProfileUrl(person);
        if (!TextUtils.isEmpty(posterUrl)) {
            Picasso.with(personProfileImageView.getContext())
                    .load(posterUrl)
                    .resize(DisplayUtility.dp2px(personProfileImageView.getContext(), 104), DisplayUtility.dp2px(personProfileImageView.getContext(), 156))
                    .centerCrop()
                    .into(personProfileImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            final Bitmap bitmap = ((BitmapDrawable) personProfileImageView.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    setUpPersonHeaderBackgroundColor(palette);
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

    private String getProfileUrl(Person person){
        String profileUrl = "";
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
                    String profilePath = person.getProfilePath();

                    profileUrl = String.format("%s%s%s", secureBaseUrl, posterSize, profilePath);
                }
            }
        }
        return profileUrl;
    }

    private String getBackdropUrl(Person person){
        String backdropUrl = "";

        Configuration configuration = MovieHubPrefs.getConfiguration(getContext());
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {

                List<String> profileSizes = images.getProfileSizes();
                if (profileSizes != null && profileSizes.size() > 0) {
                    String profileSize;
                    if (profileSizes.size() > 1) {
                        profileSize = profileSizes.get(profileSizes.size() - 2);
                    } else {
                        profileSize = profileSizes.get(profileSizes.size() - 1);
                    }

                    String secureBaseUrl = images.getSecureBaseUrl();

                    ProfileImages profileImages = person.getImages();
                    if(profileImages != null){
                        List<ProfileImage> profileImagesList = profileImages.getProfiles();
                        if(profileImagesList != null && profileImagesList.size()>0){
                            ProfileImage profileImage = profileImagesList.get(profileImagesList.size()-1);
                            if(profileImage != null){
                                String filePath = profileImage.getFilePath();

                                backdropUrl = String.format("%s%s%s", secureBaseUrl, profileSize, filePath);
                            }
                        }

                    }

                }
            }
        }

        return backdropUrl;
    }

    private void setUpFullPersonSubscription(){
        int personId = person.getId();
        final Palette profilePalette = person.getProfilePalette();

        Subscription fullPersonSubscription = Observable.combineLatest(
                movieHubService.getPersonDetails(personId),
                movieHubService.getPersonCredits(personId),
                new Func2<Person, PersonCreditsEnvelope, FullPerson>() {
                    @Override
                    public FullPerson call(Person person, PersonCreditsEnvelope personCreditsEnvelope) {
                        return new FullPerson(person, personCreditsEnvelope);
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FullPerson>() {
                    @Override
                    public void call(FullPerson fullPerson) {
                        if (fullPerson != null) {
                            person = fullPerson.getPerson();
                            person.setProfilePalette(profilePalette);
                            personCreditsEnvelope = fullPerson.getPersonCreditsEnvelope();

                            setUpBackdrop();
                            setUpBio();
                            setUpBirthplace();
                            setUpDateOfBirth();
                            setUpDateOfDeath();

                            showPersonDetailsBody();
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

        compositeSubscription.add(fullPersonSubscription);
    }

    private void showPersonDetailsBody(){
        final int targetHeight = AnimationUtility.getTargetHeight(personDetailsBodyLinearLayout);
        Animation animation = AnimationUtility.getExpandHeightAnimation(personDetailsBodyLinearLayout, targetHeight);
        // 1dp/ms
        animation.setDuration((int)(targetHeight / personDetailsBodyLinearLayout.getContext().getResources().getDisplayMetrics().density));
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setUpCast(personCreditsEnvelope);
                        setUpCrew(personCreditsEnvelope);
                    }
                }, DELAY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        personDetailsBodyLinearLayout.startAnimation(animation);
    }

    private void setUpCast(PersonCreditsEnvelope personCreditsEnvelope){
        if(personCreditsEnvelope != null){
            List<PersonCredit> cast = personCreditsEnvelope.getCast();
            if(cast != null && cast.size()>0){
                View castView = castViewStub.inflate();

                RecyclerView castRecyclerView = ButterKnife.findById(castView, R.id.cast_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                castRecyclerView.setLayoutManager(layoutManager);
                castAdapter = new PersonCreditsAdapter(getContext());
                castAdapter.setOnItemClickListener(castAdapterOnItemClickListener);
                castRecyclerView.addItemDecoration(new HorizontalLinearItemDecoration(DisplayUtility.dp2px(getActivity(), 16)));
                castRecyclerView.setAdapter(castAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(castRecyclerView);

                castAdapter.addAll(cast);
            }
        }
    }

    private void setUpCrew(PersonCreditsEnvelope personCreditsEnvelope){
        if(personCreditsEnvelope != null){
            List<PersonCredit> crew = personCreditsEnvelope.getCrew();
            if(crew != null && crew.size()>0){
                View crewView = crewViewStub.inflate();

                RecyclerView crewRecyclerView = ButterKnife.findById(crewView, R.id.crew_rv);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                crewRecyclerView.setLayoutManager(layoutManager);
                crewAdapter = new PersonCreditsAdapter(getContext());
                crewAdapter.setOnItemClickListener(crewAdapterOnItemClickListener);
                crewRecyclerView.addItemDecoration(new HorizontalLinearItemDecoration(DisplayUtility.dp2px(getActivity(), 16)));
                crewRecyclerView.setAdapter(crewAdapter);
                SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
                snapHelper.attachToRecyclerView(crewRecyclerView);

                crewAdapter.addAll(crew);
            }
        }
    }

    private void setUpPersonHeaderBackgroundColor(Palette palette){
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(getContext(), R.color.grey_600);
            int endColor = swatch.getRgb();

            AnimationUtility.animateBackgroundColorChange(personDetailsHeaderLinearLayout, startColor, endColor);
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
        String name = person.getName();
        if(!TextUtils.isEmpty(name)){
            titleTextView.setText(name);
        }
    }

    private void setUpBio(){
        String biography = person.getBiography();
        if(!TextUtils.isEmpty(biography)){
            bioTextView.setText(biography);
        } else {
            bioTextView.setText("N/A");
        }
    }

    private void setUpBirthplace(){
        String birthPlace = person.getPlaceOfBirth();
        if(!TextUtils.isEmpty(birthPlace)){
            birthplaceTextView.setText(birthPlace);
        } else {
            birthplaceTextView.setText("N/A");
        }
    }

    private void setUpDateOfBirth(){
        String dateOfBirth = person.getBirthday();
        if(!TextUtils.isEmpty(dateOfBirth)){

            Calendar calendar = DateUtility.getCalendar(dateOfBirth, PATTERN);

            String month = DateUtility.getMonth(calendar.get(Calendar.MONTH));
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);

            String formattedDateOfBirth = String.format("%s %d, %d", month, day, year);

            dateOfBirthTextView.setText(formattedDateOfBirth);
        } else {
            dateOfBirthTextView.setText("N/A");
        }
    }

    private void setUpDateOfDeath(){
        String dateOfDeath = person.getDeathday();
        if(!TextUtils.isEmpty(dateOfDeath)){

            Calendar calendar = DateUtility.getCalendar(dateOfDeath, PATTERN);

            String month = DateUtility.getMonth(calendar.get(Calendar.MONTH));
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);

            String formattedDateOfDeath = String.format("%s %d, %d", month, day, year);

            dateOfDeathTextView.setText(formattedDateOfDeath);
            dateOfDeathLinearLayout.setVisibility(View.VISIBLE);
        } else {
//            dateOfDeathTextView.setText("N/A");
            dateOfDeathLinearLayout.setVisibility(View.GONE);
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
