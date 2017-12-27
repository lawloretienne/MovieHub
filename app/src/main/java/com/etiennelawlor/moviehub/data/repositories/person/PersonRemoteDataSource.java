package com.etiennelawlor.moviehub.data.repositories.person;

import com.etiennelawlor.moviehub.MovieHubApplication;
import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.PersonCredit;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;
import com.etiennelawlor.moviehub.di.component.DaggerApplicationComponent;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRemoteDataSource implements PersonDataSourceContract.RemoteDateSource {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Injected Variables
    @Inject
    MovieHubService movieHubService;
    // endregion

    // region Constructors
    public PersonRemoteDataSource() {
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(MovieHubApplication.getInstance()))
                .build()
                .plus(new NetworkModule())
                .inject(this);
    }
    // endregion

    // region PersonDataSourceContract.RemoteDateSource Methods

    @Override
    public Single<PersonsPage> getPopularPersons(int currentPage) {
        return movieHubService.getPopularPeople(currentPage)
                .flatMap(peopleEnvelope -> Single.just(peopleEnvelope.getPersons()))
                .map(persons -> {
                    boolean isLastPage = persons.size() < PAGE_SIZE ? true : false;
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, SEVEN_DAYS);
                    return new PersonsPage(persons, currentPage, isLastPage, calendar.getTime() );
                });
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
