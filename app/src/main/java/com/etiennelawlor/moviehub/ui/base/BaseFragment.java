package com.etiennelawlor.moviehub.ui.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 6/13/15.
 */
public abstract class BaseFragment extends Fragment {

    // region Member Variables
    protected List<Call> calls;
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Timber.d("onCreate()");
        calls = new ArrayList<>();
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

//        String className = this.getClass().toString();
//        Timber.d("onDestroyView() : className - " + className);

//        if(this instanceof EventsFragment){
//            String query = ((EventsFragment)this).getQuery();
//            Timber.d("onDestroyView() : query - "+ query);
//        }

        Timber.d("onDestroyView() : calls.size() - " + calls.size());

        for (final Call call : calls) {
            Timber.d("onDestroyView() : call.cancel() - " + call.toString());

            try {
                call.cancel();
            } catch (NetworkOnMainThreadException e) {
                Timber.d("onDestroyView() : NetworkOnMainThreadException thrown");
                e.printStackTrace();
            }

//            new CancelTask().execute(call);

//            OkHttpClient client = new OkHttpClient();
//            client.getDispatcher().getExecutorService().execute(new Runnable() {
//                @Override
//                public void run() {
//                    call.cancel();
//                }
//            });
        }

        calls.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RefWatcher refWatcher = MovieHubApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
    // region

    // region Inner Classes

    private class CancelTask extends AsyncTask<Call, Void, Void> {
        @Override
        protected Void doInBackground(Call... params) {
//            Timber.d("doInBackground() : call.cancel()");

            Call call = params[0];
            call.cancel();

            return null;
        }
    }

    // endregion
}
