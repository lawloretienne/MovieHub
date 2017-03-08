package com.etiennelawlor.moviehub.data.source.televisionshows;

import com.etiennelawlor.moviehub.data.model.TelevisionShowsWrapper;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

import java.util.List;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface TelevisionShowsDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<TelevisionShowsWrapper> getPopularTelevisionShows(int currentPage);
    }

    interface LocalDateSource {
        Observable<List<TelevisionShow>> getPopularTelevisionShows(int currentPage);
        void savePopularTelevisionShows(List<TelevisionShow> televisionShows);
    }

    interface RemoteDateSource {
         Observable<List<TelevisionShow>> getPopularTelevisionShows(int currentPage);
    }
}
