package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.PersonCreditsResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRemoteDataSource implements PersonDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    @Inject
    public PersonRemoteDataSource(MovieHubService movieHubService) {
        this.movieHubService = movieHubService;
    }
    // endregion

    // region PersonDataSourceContract.RemoteDateSource Methods

    @Override
    public Single<PersonsResponse> getPopularPersons(int currentPage) {
        return movieHubService.getPopularPersons(currentPage);
    }

    @Override
    public Single<Person> getPerson(int personId) {
        return movieHubService.getPerson(personId);
    }

    @Override
    public Single<PersonCreditsResponse> getPersonCredits(int personId) {
        return movieHubService.getPersonCredits(personId);
    }

    // endregion
}
