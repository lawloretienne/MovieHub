package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
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
                (moviesDataModel, televisionShowsDataModel, personsDataModel) -> {
                    List<MovieDataModel> movies = new ArrayList<>();
                    List<TelevisionShowDataModel> televisionShows = new ArrayList<>();
                    List<PersonResponse> persons = new ArrayList<>();

                    if (moviesDataModel != null) {
                        movies = moviesDataModel.getMovies();
                    }

                    if (televisionShowsDataModel != null) {
                        televisionShows = televisionShowsDataModel.getTelevisionShows();
                    }

                    if (personsDataModel != null) {
                        persons = personsDataModel.getPersons();
                    }

                    return new SearchDomainModel(query, movies, televisionShows, persons);
                });
    }
}
