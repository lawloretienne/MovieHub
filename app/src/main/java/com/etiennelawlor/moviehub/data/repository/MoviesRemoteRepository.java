package com.etiennelawlor.moviehub.data.repository;

import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.util.NetworkLogUtility;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesRemoteRepository implements MoviesRepository {

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private MovieHubService service;
    private int currentPage = 0;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private List<Call> calls;
    // endregion

    // region Constructors
    public MoviesRemoteRepository(MovieHubService service){
        this.service = service;
    }
    // endregion

    @Override
    public Call getPopularMovies(int currentPage) {
        return service.getPopularMovies(currentPage);
    }

    @Override
    public Observable getConfiguration() {
        return null;
    }

    @Override
    public void getPopularMovies(final GetMoviesCallback getMoviesCallback) {
        if (isLoading || isLastPage) {
            return;
        }

        Call getPopularMoviesCall = service.getPopularMovies(currentPage);
        calls.add(getPopularMoviesCall);
        getPopularMoviesCall.enqueue(new Callback<MoviesEnvelope>() {
            @Override
            public void onResponse(Call<MoviesEnvelope> call, Response<MoviesEnvelope> response) {
//                moviesView.hideLoadingView();
//                moviesView.setIsLoading(false);
                isLoading = false;

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
//                            moviesView.setIsLastPage(true);

                            isLastPage = true;
                        }

                        getMoviesCallback.onSuccess(movies, 0);
                    }
                }


            }

            @Override
            public void onFailure(Call<MoviesEnvelope> call, Throwable t) {

                NetworkLogUtility.logFailure(call, t);

//                if (!call.isCanceled()){
//                    moviesView.setIsLoading(false);
//                    moviesView.hideLoadingView();
//
//                    if(NetworkUtility.isKnownException(t)){
//                        moviesView.setErrorText("Can't load data.\nCheck your network connection.");
//                        moviesView.showErrorView();
//                    }
//                }

                getMoviesCallback.onError(t, 0);
            }
        });
    }
}
