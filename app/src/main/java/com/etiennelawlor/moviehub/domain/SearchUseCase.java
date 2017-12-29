package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

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
    public Single<SearchWrapper> getSearchResponse(String query) {
        return searchRepository.getSearch(query);
    }
}
