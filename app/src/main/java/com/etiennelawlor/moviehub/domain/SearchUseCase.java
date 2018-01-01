package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.network.response.MovieResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
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
                    List<MovieResponse> movies = new ArrayList<>();
                    List<TelevisionShowResponse> televisionShows = new ArrayList<>();
                    List<PersonResponse> persons = new ArrayList<>();

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
