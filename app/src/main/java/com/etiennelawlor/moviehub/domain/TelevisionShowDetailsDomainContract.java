package com.etiennelawlor.moviehub.domain;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface TelevisionShowDetailsDomainContract {

    interface UseCase extends BaseUseCase {
        void getTelevisionShowDetails(int televisionShowId, Subscriber subscriber);
    }
}
