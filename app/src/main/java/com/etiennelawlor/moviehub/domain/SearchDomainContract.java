package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface SearchDomainContract {

    interface UseCase {
        Single<SearchWrapper> getSearchResponse(String query);
    }
}
