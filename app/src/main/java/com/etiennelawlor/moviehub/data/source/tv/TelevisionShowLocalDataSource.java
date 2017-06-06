package com.etiennelawlor.moviehub.data.source.tv;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.model.TelevisionShowsPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowLocalDataSource implements TelevisionShowDataSourceContract.LocalDateSource {

    // region Constructors
    public TelevisionShowLocalDataSource(Context context) {
    }
    // endregion

    // region TelevisionShowDataSourceContract.LocalDateSource Methods

    @Override
    public Observable<TelevisionShowsPage> getPopularTelevisionShows(int currentPage) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void savePopularTelevisionShows(TelevisionShowsPage televisionShowsPage) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int televisionShowId) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void saveTelevisionShowDetails(TelevisionShowDetailsWrapper televisionShowDetailsWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
