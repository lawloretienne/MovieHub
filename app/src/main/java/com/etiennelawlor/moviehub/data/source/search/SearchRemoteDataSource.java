package com.etiennelawlor.moviehub.data.source.search;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.SearchWrapper;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.PeopleEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowsEnvelope;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func3;

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
    public Observable<SearchWrapper> getSearch(final String query) {
        return Observable.zip(
                movieHubService.searchMovies(query, 1),
                movieHubService.searchTelevisionShows(query, 1),
                movieHubService.searchPeople(query, 1),
                new Func3<MoviesEnvelope, TelevisionShowsEnvelope, PeopleEnvelope, SearchWrapper>() {
                    @Override
                    public SearchWrapper call(MoviesEnvelope moviesEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope, PeopleEnvelope peopleEnvelope) {
                        List<Movie> movies = new ArrayList<>();
                        List<TelevisionShow> televisionShows = new ArrayList<>();
                        List<Person> persons = new ArrayList<>();

                        if(moviesEnvelope!=null){
                            movies = moviesEnvelope.getMovies();
                        }

                        if(televisionShowsEnvelope!=null){
                            televisionShows = televisionShowsEnvelope.getTelevisionShows();
                        }

                        if(peopleEnvelope!=null){
                            persons = peopleEnvelope.getPersons();
                        }

                        return new SearchWrapper(query, movies, televisionShows, persons);
                    }
                });
    }

    // endregion
}
