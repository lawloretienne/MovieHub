package com.etiennelawlor.moviehub.data.network;

import com.etiennelawlor.moviehub.data.network.response.ConfigurationResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesResponse;
import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.MoviesResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsResponse;

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
    Single<MoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/{movieId}")
    Single<MovieResponse> getMovie(@Path("movieId") long movieId);

    @GET("movie/{movieId}/credits")
    Single<MovieCreditsResponse> getMovieCredits(@Path("movieId") long movieId);

    @GET("movie/{movieId}/similar")
    Single<MoviesResponse> getSimilarMovies(@Path("movieId") long movieId);

    @GET("movie/{movieId}/release_dates")
    Single<MovieReleaseDatesResponse> getMovieReleaseDates(@Path("movieId") long movieId);

    @GET("tv/popular")
    Single<TelevisionShowsResponse> getPopularTelevisionShows(@Query("page") int page);

    @GET("tv/{tvId}")
    Single<TelevisionShowResponse> getTelevisionShow(@Path("tvId") long tvId);

    @GET("tv/{tvId}/credits")
    Single<TelevisionShowCreditsResponse> getTelevisionShowCredits(@Path("tvId") long tvId);

    @GET("tv/{tvId}/similar")
    Single<TelevisionShowsResponse> getSimilarTelevisionShows(@Path("tvId") long tvId);

    @GET("tv/{tvId}/content_ratings")
    Single<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(@Path("tvId") long tvId);

    @GET("person/popular")
    Single<PersonsResponse> getPopularPersons(@Query("page") int page);

    @GET("person/{personId}?append_to_response=images")
    Single<PersonResponse> getPerson(@Path("personId") long personId);

    @GET("person/{personId}/combined_credits")
    Single<PersonCreditsResponse> getPersonCredits(@Path("personId") long personId);

    @GET("configuration")
    Single<ConfigurationResponse> getConfiguration();

    @GET("search/movie")
    Single<MoviesResponse> getMovieSearchResults(@Query("query") String query, @Query("page") int page);

    @GET("search/tv")
    Single<TelevisionShowsResponse> getTelevisionShowSearchResults(@Query("query") String query, @Query("page") int page);

    @GET("search/person")
    Single<PersonsResponse> getPersonSearchResults(@Query("query") String query, @Query("page") int page);

}
