package com.etiennelawlor.moviehub.data.source.person;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.PersonsPage;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.PersonCredit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonRemoteDataSource implements PersonDataSourceContract.RemoteDateSource {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public PersonRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region PersonDataSourceContract.RemoteDateSource Methods

    @Override
    public Observable<PersonsPage> getPopularPersons(int currentPage) {
        return movieHubService.getPopularPeople(currentPage)
                .flatMap(peopleEnvelope -> Observable.just(peopleEnvelope.getPersons()))
                .map(persons -> {
                    boolean isLastPage = persons.size() < PAGE_SIZE ? true : false;
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, SEVEN_DAYS);
                    return new PersonsPage(persons, currentPage, isLastPage, calendar.getTime() );
                });
    }

    @Override
    public Observable<PersonDetailsWrapper> getPersonDetails(int personId) {
        return Observable.zip(
                movieHubService.getPersonDetails(personId),
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
