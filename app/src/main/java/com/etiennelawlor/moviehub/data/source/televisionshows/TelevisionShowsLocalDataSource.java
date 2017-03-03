package com.etiennelawlor.moviehub.data.source.televisionshows;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowsLocalDataSource implements TelevisionShowsDataSourceContract.LocalDateSource {

    // region Constructors
    public TelevisionShowsLocalDataSource(Context context) {
    }
    // endregion

    // region TelevisionShowsDataSourceContract.LocalDateSource Methods

    @Override
    public Observable<List<TelevisionShow>> getPopularTelevisionShows(int currentPage) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void savePopularTelevisionShows(List<TelevisionShow> televisionShows) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
