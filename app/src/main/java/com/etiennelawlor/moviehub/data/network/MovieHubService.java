package com.etiennelawlor.moviehub.data.network;

import com.etiennelawlor.moviehub.data.network.response.Configuration;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by etiennelawlor on 5/17/16.
 */
public interface MovieHubService {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Single<MoviesEnvelope> getPopularMovies(@Query("page") int page);

    @GET("movie/{movieId}")
    Single<Movie> getMovie(@Path("movieId") long movieId);

    @GET("movie/{movieId}/credits")
    Single<MovieCreditsResponse> getMovieCredits(@Path("movieId") long movieId);

    @GET("movie/{movieId}/similar")
    Single<MoviesEnvelope> getSimilarMovies(@Path("movieId") long movieId);

    @GET("movie/{movieId}/release_dates")
    Single<MovieReleaseDatesResponse> getMovieReleaseDates(@Path("movieId") long movieId);

    @GET("tv/popular")
    Single<TelevisionShowsEnvelope> getPopularTelevisionShows(@Query("page") int page);

    @GET("tv/{tvId}")
    Single<TelevisionShow> getTelevisionShow(@Path("tvId") long tvId);

    @GET("tv/{tvId}/credits")
    Single<TelevisionShowCreditsEnvelope> getTelevisionShowCredits(@Path("tvId") long tvId);

    @GET("tv/{tvId}/similar")
    Single<TelevisionShowsEnvelope> getSimilarTelevisionShows(@Path("tvId") long tvId);

    @GET("tv/{tvId}/content_ratings")
    Single<TelevisionShowContentRatingsEnvelope> getTelevisionShowContentRatings(@Path("tvId") long tvId);

    @GET("person/popular")
    Single<PersonsEnvelope> getPopularPersons(@Query("page") int page);

    @GET("person/{personId}?append_to_response=images")
    Single<Person> getPerson(@Path("personId") long personId);

    @GET("person/{personId}/combined_credits")
    Single<PersonCreditsEnvelope> getPersonCredits(@Path("personId") long personId);

    @GET("configuration")
    Single<Configuration> getConfiguration();

    @GET("search/movie")
    Single<MoviesEnvelope> getMovieSearchResults(@Query("query") String query, @Query("page") int page);

    @GET("search/tv")
    Single<TelevisionShowsEnvelope> getTelevisionShowSearchResults(@Query("query") String query, @Query("page") int page);

    @GET("search/person")
    Single<PersonsEnvelope> getPersonSearchResults(@Query("query") String query, @Query("page") int page);

}
