package com.etiennelawlor.moviehub.data.repositories.search;

import android.content.Context;

import com.etiennelawlor.moviehub.data.network.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.ServiceGenerator;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchRemoteDataSource implements SearchDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public SearchRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region SearchDataSourceContract.RemoteDateSource Methods
    @Override
    public Single<SearchWrapper> getSearch(final String query) {
        return Single.zip(
                movieHubService.searchMovies(query, 1),
                movieHubService.searchTelevisionShows(query, 1),
                movieHubService.searchPeople(query, 1),
                (moviesEnvelope, televisionShowsEnvelope, peopleEnvelope) -> {
                    List<Movie> movies = new ArrayList<>();
                    List<TelevisionShow> televisionShows = new ArrayList<>();
                    List<Person> persons = new ArrayList<>();

                    if (moviesEnvelope != null) {
                        movies = moviesEnvelope.getMovies();
                    }

                    if (televisionShowsEnvelope != null) {
                        televisionShows = televisionShowsEnvelope.getTelevisionShows();
                    }

                    if (peopleEnvelope != null) {
                        persons = peopleEnvelope.getPersons();
                    }

                    return new SearchWrapper(query, movies, televisionShows, persons);
                });
    }

    // endregion
}
