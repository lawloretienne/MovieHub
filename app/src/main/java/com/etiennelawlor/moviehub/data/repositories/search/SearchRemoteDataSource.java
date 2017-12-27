package com.etiennelawlor.moviehub.data.repositories.search;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.di.component.DaggerApplicationComponent;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class SearchRemoteDataSource implements SearchDataSourceContract.RemoteDateSource {

    // region Injected Variables
    @Inject
    MovieHubService movieHubService;
    // endregion

    // region Constructors
    public SearchRemoteDataSource() {
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(MovieHubApplication.getInstance()))
                .build()
                .plus(new NetworkModule())
                .inject(this);
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
