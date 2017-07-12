package com.etiennelawlor.moviehub.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by etiennelawlor on 6/13/15.
 */
public abstract class BaseFragment extends Fragment {

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Timber.d("onCreate()");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Timber.d("onViewCreated()");
    }

    @Override
    public void onStop() {
        super.onStop();
//        Timber.d("onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Timber.d("onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RefWatcher refWatcher = MovieHubApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
    // region
}
