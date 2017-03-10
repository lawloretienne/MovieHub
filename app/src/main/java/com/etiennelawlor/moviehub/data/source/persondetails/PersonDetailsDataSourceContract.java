package com.etiennelawlor.moviehub.data.source.persondetails;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonDetailsDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<PersonDetailsWrapper> getPersonDetails(int personId);
    }

    interface LocalDateSource {
        Observable<PersonDetailsWrapper> getPersonDetails(int personId);
        void savePersonDetails(PersonDetailsWrapper personDetailsWrapper);
    }

    interface RemoteDateSource {
         Observable<PersonDetailsWrapper> getPersonDetails(int personId);
    }
}
