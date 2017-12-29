package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.PeopleEnvelope;
import com.etiennelawlor.moviehub.data.network.response.PersonCredit;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;

import java.util.ArrayList;
import java.util.List;

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
    public Single<PeopleEnvelope> getPopularPersons(int currentPage) {
        return movieHubService.getPopularPeople(currentPage);
    }

    @Override
    public Single<PersonDetailsWrapper> getPersonDetails(int personId) {
        return Single.zip(
                movieHubService.getPerson(personId),
                movieHubService.getPersonCredits(personId),
                (person, personCreditsEnvelope) -> {
                    List<PersonCredit> cast = new ArrayList<>();
                    List<PersonCredit> crew = new ArrayList<>();

                    if(personCreditsEnvelope!=null){
                        cast = personCreditsEnvelope.getCast();
                    }

                    if(personCreditsEnvelope!=null){
                        crew = personCreditsEnvelope.getCrew();
                    }

                    return new PersonDetailsWrapper(person, cast, crew);
                });
    }

    // endregion
}
