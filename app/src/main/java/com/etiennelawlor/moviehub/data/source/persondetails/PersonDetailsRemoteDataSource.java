package com.etiennelawlor.moviehub.data.source.persondetails;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.remote.response.PersonCredit;
import com.etiennelawlor.moviehub.data.remote.response.PersonCreditsEnvelope;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class PersonDetailsRemoteDataSource implements PersonDetailsDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public PersonDetailsRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region PersonDetailsDataSourceContract.RemoteDateSource Methods
    @Override
    public Observable<PersonDetailsWrapper> getPersonDetails(int personId) {
        return Observable.zip(
                movieHubService.getPersonDetails(personId),
                movieHubService.getPersonCredits(personId),
                new Func2<Person, PersonCreditsEnvelope, PersonDetailsWrapper>() {
                    @Override
                    public PersonDetailsWrapper call(Person person, PersonCreditsEnvelope personCreditsEnvelope) {
                        List<PersonCredit> cast = new ArrayList<>();
                        List<PersonCredit> crew = new ArrayList<>();

                        if(personCreditsEnvelope!=null){
                            cast = personCreditsEnvelope.getCast();
                        }

                        if(personCreditsEnvelope!=null){
                            crew = personCreditsEnvelope.getCrew();
                        }

                        return new PersonDetailsWrapper(person, cast, crew);
                    }
                });
    }

    // endregion
}
