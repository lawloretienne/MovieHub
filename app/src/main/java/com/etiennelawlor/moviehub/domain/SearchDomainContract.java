package com.etiennelawlor.moviehub.domain;

import io.reactivex.observers.DisposableSingleObserver;
import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface SearchDomainContract {

    interface UseCase extends BaseUseCase {
        void getSearchResponse(String query, Subscriber subscriber);
    }
}
