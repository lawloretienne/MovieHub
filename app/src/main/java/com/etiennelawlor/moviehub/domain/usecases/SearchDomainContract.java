package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface SearchDomainContract {

    interface UseCase {
        Single<SearchDomainModel> getSearchResponse(String query);
    }
}
