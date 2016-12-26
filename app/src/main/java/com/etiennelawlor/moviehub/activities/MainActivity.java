package com.etiennelawlor.moviehub.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.fragments.MoviesFragment;
import com.etiennelawlor.moviehub.fragments.PersonsFragment;
import com.etiennelawlor.moviehub.fragments.TelevisionShowsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // region Views
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    // endregion

    // region Lifecycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MovieHub_MainActivity);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        switch (item.getItemId()) {
                            case R.id.action_movies:
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                        .replace(R.id.content_fl, MoviesFragment.newInstance(), "")
                                        .commit();
                                break;
                            case R.id.action_tv_shows:
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                        .replace(R.id.content_fl, TelevisionShowsFragment.newInstance(), "")
                                        .commit();
                                break;
                            case R.id.action_people:
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                        .replace(R.id.content_fl, PersonsFragment.newInstance(), "")
                                        .commit();
                                break;
                        }
                        return false;
                    }
                });
    }
    // endregion
}
