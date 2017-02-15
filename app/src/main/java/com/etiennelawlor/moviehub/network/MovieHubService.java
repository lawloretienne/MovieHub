package com.etiennelawlor.moviehub.network;

import com.etiennelawlor.moviehub.network.models.Configuration;
import com.etiennelawlor.moviehub.network.models.Movie;
import com.etiennelawlor.moviehub.network.models.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.MoviesEnvelope;
import com.etiennelawlor.moviehub.network.models.PeopleEnvelope;
import com.etiennelawlor.moviehub.network.models.Person;
import com.etiennelawlor.moviehub.network.models.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.network.models.TelevisionShow;
import com.etiennelawlor.moviehub.network.models.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.network.models.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.TelevisionShowsEnvelope;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by etiennelawlor on 5/17/16.
 */
public interface MovieHubService {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Observable<MoviesEnvelope> getPopularMovies(@Query("page") int page);

    @GET("movie/{movieId}")
    Observable<Movie> getMovieDetails(@Path("movieId") long movieId);

    @GET("movie/{movieId}/credits")
    Observable<MovieCreditsEnvelope> getMovieCredits(@Path("movieId") long movieId);

    @GET("movie/{movieId}/similar")
    Observable<MoviesEnvelope> getSimilarMovies(@Path("movieId") long movieId);

    @GET("movie/{movieId}/release_dates")
    Observable<MovieReleaseDatesEnvelope> getMovieReleaseDates(@Path("movieId") long movieId);

    @GET("tv/popular")
    Observable<TelevisionShowsEnvelope> getPopularTelevisionShows(@Query("page") int page);

    @GET("tv/{tvId}")
    Observable<TelevisionShow> getTelevisionShowDetails(@Path("tvId") long tvId);

    @GET("tv/{tvId}/credits")
    Observable<TelevisionShowCreditsEnvelope> getTelevisionShowCredits(@Path("tvId") long tvId);

    @GET("tv/{tvId}/similar")
    Observable<TelevisionShowsEnvelope> getSimilarTelevisionShows(@Path("tvId") long tvId);

    @GET("tv/{tvId}/content_ratings")
    Observable<TelevisionShowContentRatingsEnvelope> getTelevisionShowContentRatings(@Path("tvId") long tvId);

    @GET("person/popular")
    Observable<PeopleEnvelope> getPopularPeople(@Query("page") int page);

    @GET("person/{personId}?append_to_response=images")
    Observable<Person> getPersonDetails(@Path("personId") long personId);

    @GET("person/{personId}/combined_credits")
    Observable<PersonCreditsEnvelope> getPersonCredits(@Path("personId") long personId);

    @GET("configuration")
    Observable<Configuration> getConfiguration();

    @GET("search/movie")
    Observable<MoviesEnvelope> searchMovies(@Query("query") String query, @Query("page") int page);

    @GET("search/tv")
    Observable<TelevisionShowsEnvelope> searchTelevisionShows(@Query("query") String query, @Query("page") int page);

    @GET("search/person")
    Observable<PeopleEnvelope> searchPeople(@Query("query") String query, @Query("page") int page);

}
