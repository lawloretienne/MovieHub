package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.domain.composers.SearchDomainModelComposer;
import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 12/22/17.
 */

public class SearchUseCase implements SearchDomainContract.UseCase {

    // region Member Variables
    private final SearchDataSourceContract.Repository searchRepository;
    private final SearchDomainModelComposer searchDomainModelComposer = new SearchDomainModelComposer();
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
                (moviesDataModel, televisionShowsDataModel, personsDataModel) ->
                    searchDomainModelComposer.compose(moviesDataModel, televisionShowsDataModel, personsDataModel, query));
    }
}
