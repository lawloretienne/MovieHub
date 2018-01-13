package com.etiennelawlor.moviehub.presentation.main;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.presentation.movies.MoviesFragment;
import com.etiennelawlor.moviehub.presentation.persons.PersonsFragment;
import com.etiennelawlor.moviehub.presentation.search.SearchActivity;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsFragment;
import com.etiennelawlor.moviehub.util.FontCache;
import com.etiennelawlor.moviehub.util.TrestleUtility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    // region Views
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.search_cv)
    CardView searchCardView;
    // endregion

    // region Member Variables
    private Typeface font;
    private MainContract.Presenter mainPresenter;
    // endregion

    // region Listeners
    @OnClick(R.id.search_cv)
    public void onSearchCardViewClicked(View view) {
        mainPresenter.viewSearch();
    }
    // endregion

    // region Lifecycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MovieHub_MainActivity);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        font = FontCache.getTypeface("Lato-Medium.ttf", this);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_fl);
        if (fragment == null) {
            fragment = MoviesFragment.newInstance(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.content_fl, fragment, "")
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }

        formatMenuItems();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    if(!item.isChecked()){
                        item.setChecked(true);
                        Fragment fragment1;
                        switch (item.getItemId()) {
                            case R.id.action_movies:
                                fragment1 = MoviesFragment.newInstance();
                                break;
                            case R.id.action_tv_shows:
                                fragment1 = TelevisionShowsFragment.newInstance();
                                break;
                            case R.id.action_people:
                                fragment1 = PersonsFragment.newInstance();
                                break;
                            default:
                                fragment1 = MoviesFragment.newInstance();
                                break;
                        }

                        getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.content_fl, fragment1, "")
                                .commit();
                    } else {
                        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.content_fl);
                        if(fragment1 instanceof MoviesFragment){
                            ((MoviesFragment) fragment1).scrollToTop();
                        } else if(fragment1 instanceof TelevisionShowsFragment){
                            ((TelevisionShowsFragment) fragment1).scrollToTop();
                        } else if(fragment1 instanceof PersonsFragment){
                            ((PersonsFragment) fragment1).scrollToTop();
                        }
                    }
                    return false;
                });
    }
    // endregion

    // region MainContract.View Methods

    @Override
    public void viewSearch() {
        Window window = getWindow();
//        window.setStatusBarColor(primaryDark);

        Resources resources = searchCardView.getResources();
        Pair<View, String> searchPair  = getSearchViewPair(searchCardView, resources.getString(R.string.transition_search));

        ActivityOptionsCompat options = getActivityOptionsCompat(searchPair);

        window.setExitTransition(null);
        ActivityCompat.startActivity(this, SearchActivity.createIntent(this), options.toBundle());
    }

    // endregion

    // region Helper Methods
    private void formatMenuItems() {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);

            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            applyFontToMenuItem(mi);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        mi.setTitle(TrestleUtility.getFormattedText(mi.getTitle().toString(), font));
    }

    private ActivityOptionsCompat getActivityOptionsCompat(Pair pair){
        ActivityOptionsCompat options = null;

        Pair<View, String> navigationBarPair  = getNavigationBarPair();
        Pair<View, String> statusBarPair = getStatusBarPair();

        if(pair!=null && statusBarPair!= null && navigationBarPair!= null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    pair, statusBarPair, navigationBarPair);
        } else if(pair != null && statusBarPair != null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    pair, statusBarPair);
        } else if(pair != null && navigationBarPair != null){
            options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    pair, navigationBarPair);
        }

        return options;
    }

    private Pair<View, String> getStatusBarPair(){
        Pair<View, String> pair = null;
        View statusBar = findViewById(android.R.id.statusBarBackground);
        if(statusBar != null)
            pair = Pair.create(statusBar, statusBar.getTransitionName());
        return pair;
    }

    private Pair<View, String> getNavigationBarPair(){
        Pair<View, String> pair = null;
        View navigationBar = findViewById(android.R.id.navigationBarBackground);
        if(navigationBar != null)
            pair = Pair.create(navigationBar, navigationBar.getTransitionName());
        return pair;
    }

    private Pair<View, String> getSearchViewPair(View view, String transition){
        Pair<View, String> searchPair = null;
        View searchView = view.findViewById(R.id.search_cv);
        if(searchView != null){
            searchPair = Pair.create(searchView, transition);
        }

        return searchPair;
    }
    // endregion
}
