package com.etiennelawlor.moviehub.data.source.persons;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.PeopleEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.Person;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonsRemoteDataSource implements PersonsDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public PersonsRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region PersonsDataSourceContract.RemoteDateSource Methods

    @Override
    public Observable<List<Person>> getPopularPersons(int currentPage) {
        return movieHubService.getPopularPeople(currentPage)
                .flatMap(new Func1<PeopleEnvelope, Observable<List<Person>>>() {
                    @Override
                    public Observable<List<Person>> call(PeopleEnvelope peopleEnvelope) {
                        return Observable.just(peopleEnvelope.getPersons());
                    }
                });
    }


    // endregion
}
