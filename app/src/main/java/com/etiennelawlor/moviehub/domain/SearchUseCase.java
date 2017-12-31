package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 12/22/17.
 */

public class SearchUseCase implements SearchDomainContract.UseCase {

    // region Member Variables
    private final SearchDataSourceContract.Repository searchRepository;
    // endregion

    // region Constructors

    public SearchUseCase(SearchDataSourceContract.Repository searchRepository) {
        this.searchRepository = searchRepository;
    }

    // endregion
    @Override
    public Single<SearchDomainModel> getSearchResponse(String query) {
        return Single.zip(
                searchRepository.getMovieSearchResults(query, 1),
                searchRepository.getTelevisionShowSearchResults(query, 1),
                searchRepository.getPersonSearchResults(query, 1),
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

                    return new SearchDomainModel(query, movies, televisionShows, persons);
                });
    }
}
