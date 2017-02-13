package com.etiennelawlor.moviehub.ui.movies;

import android.os.NetworkOnMainThreadException;
import android.view.View;

import com.etiennelawlor.moviehub.data.local.PreferencesHelper;
import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.repository.MoviesRepository;
import com.etiennelawlor.moviehub.util.NetworkLogUtility;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesContract.Presenter {

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private final MoviesRepository moviesRepository;
    private final MoviesContract.View moviesView;

    private CompositeSubscription compositeSubscription;
    private List<Call> calls;
    // endregion

    // region Callbacks
    private Callback<MoviesEnvelope> getPopularMoviesFirstFetchCallback = new Callback<MoviesEnvelope>() {
        @Override
        public void onResponse(Call<MoviesEnvelope> call, Response<MoviesEnvelope> response) {
            moviesView.hideLoadingView();
            moviesView.setIsLoading(false);

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if(responseCode == 504) { // 504 Unsatisfiable Request (only-if-cached)
//                    moviesView.setErrorText("Can't load data.\nCheck your network connection.");
//                    moviesView.showErrorView();

                    return;
                }
            }

            MoviesEnvelope moviesEnvelope = response.body();

            if(moviesEnvelope != null){
                List<Movie> movies = moviesEnvelope.getMovies();
                if(movies != null){
                    if(movies.size()>0)
                        moviesView.addMoviesToAdapter(movies);

                    if(movies.size() >= PAGE_SIZE){
                        moviesView.addFooter();
                    } else {
                        moviesView.setIsLastPage(true);
                    }
                }
            }

            if(moviesView.isAdapterEmpty()){
                moviesView.hideEmptyView();
            }
        }

        @Override
        public void onFailure(Call<MoviesEnvelope> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()){
                moviesView.setIsLoading(false);
                moviesView.hideLoadingView();

                if(NetworkUtility.isKnownException(t)){
                    moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                    moviesView.showErrorView();
                }
            }
        }
    };

    private Callback<MoviesEnvelope> getPopularMoviesNextFetchCallback = new Callback<MoviesEnvelope>() {
        @Override
        public void onResponse(Call<MoviesEnvelope> call, Response<MoviesEnvelope> response) {
            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if(responseCode == 504) { // 504 Unsatisfiable Request (only-if-cached)
//                    errorTextView.setText("Can't load data.\nCheck your network connection.");
//                    errorLinearLayout.setVisibility(View.VISIBLE);
                    return;
                }
            }

            moviesView.removeFooter();
            moviesView.setIsLoading(false);

            MoviesEnvelope moviesEnvelope = response.body();

            if(moviesEnvelope != null){
                List<Movie> movies = moviesEnvelope.getMovies();
                if(movies != null){
                    if(movies.size()>0)
                        moviesView.addMoviesToAdapter(movies);

                    if(movies.size() >= PAGE_SIZE){
                        moviesView.addFooter();
                    } else {
                        moviesView.setIsLastPage(true);
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<MoviesEnvelope> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()){
                if(NetworkUtility.isKnownException(t)){
                    moviesView.updateFooter(MoviesContract.View.FooterType.ERROR);
                }
            }
        }
    };
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesRepository moviesRepository, MoviesContract.View moviesView) {
        this.moviesRepository = moviesRepository;
        this.moviesView = moviesView;
    }
    // endregion

    @Override
    public void loadMovies(int currentPage) {
        if(currentPage == 0) {

//            moviesRepository.getPopularMovies(currentPage, Callback() {
//                @Override
//                public void onResponse () {
//                }
//
//                @Override
//                public void onFailure () {
//                }
//            }

            Call getPopularMoviesCall = moviesRepository.getPopularMovies(currentPage);
            calls.add(getPopularMoviesCall);
            getPopularMoviesCall.enqueue(getPopularMoviesFirstFetchCallback);
        } else {
            Call getPopularMoviesCall = moviesRepository.getPopularMovies(currentPage);
            calls.add(getPopularMoviesCall);
            getPopularMoviesCall.enqueue(getPopularMoviesNextFetchCallback);
        }
    }

    @Override
    public void reloadMovies(int currentPage) {
        if(currentPage == 0){
            moviesView.hideEmptyView();
            moviesView.hideErrorView();
            moviesView.showLoadingView();

            Call getPopularMoviesCall = moviesRepository.getPopularMovies(currentPage);
            calls.add(getPopularMoviesCall);
            getPopularMoviesCall.enqueue(getPopularMoviesFirstFetchCallback);
        } else {
            moviesView.updateFooter(MoviesContract.View.FooterType.LOAD_MORE);

            Call getPopularMoviesCall = moviesRepository.getPopularMovies(currentPage);
            calls.add(getPopularMoviesCall);
            getPopularMoviesCall.enqueue(getPopularMoviesNextFetchCallback);
        }
    }

    @Override
    public void getConfiguration() {
        Subscription subscription = moviesRepository.getConfiguration()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Configuration>() {
                    @Override
                    public void call(Configuration configuration) {
                        if(configuration != null){
//                            PreferencesHelper.setConfiguration(getContext(), configuration);
                            moviesView.saveConfiguration(configuration);

//                                Call getPopularMoviesCall = movieHubService.getPopularMovies(currentPage);
//                                calls.add(getPopularMoviesCall);
//                                getPopularMoviesCall.enqueue(getPopularMoviesFirstFetchCallback);

                            loadMovies(0);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
//                        progressBar.setVisibility(View.GONE);
                        moviesView.showLoadingView();
                        if (NetworkUtility.isKnownException(throwable)) {
//                            errorTextView.setText("Can't load data.\nCheck your network connection.");
//                            errorLinearLayout.setVisibility(View.VISIBLE);
                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                            moviesView.showErrorView();
                        }
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onAttachView(MoviesContract.View view) {
        calls = new ArrayList<>();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDetachView() {
        compositeSubscription.unsubscribe();

        for (final Call call : calls) {
            Timber.d("onDetachView() : call.cancel() - " + call.toString());

            try {
                call.cancel();
            } catch (NetworkOnMainThreadException e) {
                Timber.e("onDetachView() : NetworkOnMainThreadException thrown");
                e.printStackTrace();
            }
        }

        calls.clear();
    }

    @Override
    public void unsubscribeCompositeSubscription() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public void viewMovieDetails(Movie movie) {
        moviesView.viewMovieDetails(movie);
    }
}
