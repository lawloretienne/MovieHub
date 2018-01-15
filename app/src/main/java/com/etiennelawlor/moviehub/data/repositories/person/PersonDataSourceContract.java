package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface PersonDataSourceContract {

    interface Repository {
        Single<PersonsDataModel> getPopularPersons(int currentPage);
        Single<PersonDataModel> getPerson(long personId);
        Single<PersonCreditsDataModel> getPersonCredits(long personId);
    }

    interface LocalDateSource {
        Maybe<PersonsDataModel> getPopularPersons(int currentPage);
        void savePopularPersons(PersonsDataModel personsDataModel);
        Maybe<PersonDataModel> getPerson(long personId);
        void savePerson(PersonDataModel person);
        Maybe<PersonCreditsDataModel> getPersonCredits(long personId);
        void savePersonCredits(PersonCreditsDataModel personCreditsResponse);
    }

    interface RemoteDateSource {
        Single<PersonsResponse> getPopularPersons(int currentPage);
        Single<PersonResponse> getPerson(long personId);
        Single<PersonCreditsResponse> getPersonCredits(long personId);
    }
}
