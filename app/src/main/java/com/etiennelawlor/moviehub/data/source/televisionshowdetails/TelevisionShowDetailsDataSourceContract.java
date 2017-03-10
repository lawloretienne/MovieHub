package com.etiennelawlor.moviehub.data.source.televisionshowdetails;

import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface TelevisionShowDetailsDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
    }

    interface LocalDateSource {
        Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
        void saveTelevisionShowDetails(TelevisionShowDetailsWrapper televisionShowDetailsWrapper);
    }

    interface RemoteDateSource {
         Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int currentPage);
    }
}
