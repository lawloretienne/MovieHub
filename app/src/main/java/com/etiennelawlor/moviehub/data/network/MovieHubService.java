package com.etiennelawlor.moviehub.data.network;

import com.etiennelawlor.moviehub.data.network.response.Configuration;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MovieCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDatesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PeopleEnvelope;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;

import io.reactivex.Single;
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
    Single<MoviesEnvelope> getPopularMovies(@Query("page") int page);

    @GET("movie/{movieId}")
    Observable<Movie> getMovie(@Path("movieId") long movieId);

    @GET("movie/{movieId}/credits")
    Observable<MovieCreditsEnvelope> getMovieCredits(@Path("movieId") long movieId);

    @GET("movie/{movieId}/similar")
    Observable<MoviesEnvelope> getSimilarMovies(@Path("movieId") long movieId);

    @GET("movie/{movieId}/release_dates")
    Observable<MovieReleaseDatesEnvelope> getMovieReleaseDates(@Path("movieId") long movieId);

    @GET("tv/popular")
    Single<TelevisionShowsEnvelope> getPopularTelevisionShows(@Query("page") int page);

    @GET("tv/{tvId}")
    Observable<TelevisionShow> getTelevisionShow(@Path("tvId") long tvId);

    @GET("tv/{tvId}/credits")
    Observable<TelevisionShowCreditsEnvelope> getTelevisionShowCredits(@Path("tvId") long tvId);

    @GET("tv/{tvId}/similar")
    Observable<TelevisionShowsEnvelope> getSimilarTelevisionShows(@Path("tvId") long tvId);

    @GET("tv/{tvId}/content_ratings")
    Observable<TelevisionShowContentRatingsEnvelope> getTelevisionShowContentRatings(@Path("tvId") long tvId);

    @GET("person/popular")
    Observable<PeopleEnvelope> getPopularPeople(@Query("page") int page);

    @GET("person/{personId}?append_to_response=images")
    Observable<Person> getPerson(@Path("personId") long personId);

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
