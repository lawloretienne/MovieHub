package com.etiennelawlor.moviehub.domain;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface PersonDetailsDomainContract {

    interface UseCase extends BaseUseCase {
        void getPersonDetails(int personId, Subscriber subscriber);
    }
}
