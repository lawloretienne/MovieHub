package com.etiennelawlor.moviehub.data.source.televisionshows;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowsEnvelope;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowsRemoteDataSource implements TelevisionShowsDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public TelevisionShowsRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region TelevisionShowsDataSourceContract.RemoteDateSource Methods
    @Override
    public Observable<List<TelevisionShow>> getPopularTelevisionShows(int currentPage) {
        return movieHubService.getPopularTelevisionShows(currentPage)
                .flatMap(new Func1<TelevisionShowsEnvelope, Observable<List<TelevisionShow>>>() {
                    @Override
                    public Observable<List<TelevisionShow>> call(TelevisionShowsEnvelope televisionShowsEnvelope) {
                        return Observable.just(televisionShowsEnvelope.getTelevisionShows());
                    }
                });
    }

    // endregion
}
