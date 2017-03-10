package com.etiennelawlor.moviehub.data.source.moviedetails;

import android.content.Context;
import android.text.TextUtils;

import com.etiennelawlor.moviehub.data.model.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MovieCredit;
import com.etiennelawlor.moviehub.data.remote.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.MovieReleaseDate;
import com.etiennelawlor.moviehub.data.remote.response.MovieReleaseDateEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func4;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieDetailsRemoteDataSource implements MovieDetailsDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public MovieDetailsRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region MoviesDataSourceContract.RemoteDateSource Methods
    @Override
    public Observable<MovieDetailsWrapper> getMovieDetails(int movieId) {
        return Observable.zip(
                movieHubService.getMovieDetails(movieId),
                movieHubService.getMovieCredits(movieId),
                movieHubService.getSimilarMovies(movieId),
                movieHubService.getMovieReleaseDates(movieId),
                new Func4<Movie, MovieCreditsEnvelope, MoviesEnvelope, MovieReleaseDatesEnvelope, MovieDetailsWrapper>() {
                    @Override
                    public MovieDetailsWrapper call(Movie movie, MovieCreditsEnvelope movieCreditsEnvelope, MoviesEnvelope moviesEnvelope, MovieReleaseDatesEnvelope movieReleaseDatesEnvelope) {
                        List<MovieCredit> cast = new ArrayList<>();
                        List<MovieCredit> crew = new ArrayList<>();
                        List<Movie> similarMovies = new ArrayList<>();
                        String rating = "";

                        if(movieCreditsEnvelope!=null){
                            cast = movieCreditsEnvelope.getCast();
                        }

                        if(movieCreditsEnvelope!=null){
                            crew = movieCreditsEnvelope.getCrew();
                        }

                        if(moviesEnvelope!=null){
                            similarMovies = moviesEnvelope.getMovies();
                        }

                        if(movieReleaseDatesEnvelope!=null){
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
                        }

                        return new MovieDetailsWrapper(movie, cast, crew, similarMovies, rating);
                    }
                });
    }

    // endregion
}
