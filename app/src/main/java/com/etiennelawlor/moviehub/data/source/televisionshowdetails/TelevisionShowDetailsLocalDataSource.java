package com.etiennelawlor.moviehub.data.source.televisionshowdetails;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowDetailsLocalDataSource implements TelevisionShowDetailsDataSourceContract.LocalDateSource {

    // region Constructors
    public TelevisionShowDetailsLocalDataSource(Context context) {
    }
    // endregion

    // region MoviesDataSourceContract.LocalDateSource Methods

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
