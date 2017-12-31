package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsDataModel;

import io.reactivex.Maybe;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowLocalDataSource implements TelevisionShowDataSourceContract.LocalDateSource {

    // region Constructors
    public TelevisionShowLocalDataSource() {
    }
    // endregion

    // region TelevisionShowDataSourceContract.LocalDateSource Methods

    @Override
    public Maybe<TelevisionShowsDataModel> getPopularTelevisionShows(int currentPage) {
        TelevisionShowsDataModel televisionShowsDataModel = RealmUtility.getTelevisionShowsDataModel(currentPage);
        if(televisionShowsDataModel == null)
            return Maybe.empty();
        else
            return Maybe.just(televisionShowsDataModel);
    }

    @Override
    public void savePopularTelevisionShows(TelevisionShowsDataModel televisionShowsDataModel) {
        RealmUtility.saveTelevisionShowsDataModel(televisionShowsDataModel);
    }

    @Override
    public Maybe<TelevisionShow> getTelevisionShow(int tvId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShow(TelevisionShow televisionShow) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<TelevisionShowCreditsEnvelope> getTelevisionShowCredits(int tvId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShowCredits(TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<TelevisionShowsEnvelope> getSimilarTelevisionShows(int tvId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveSimilarTelevisionShows(TelevisionShowsEnvelope televisionShowsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    @Override
    public Maybe<TelevisionShowContentRatingsEnvelope> getTelevisionShowContentRatings(int tvId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShowContentRatings(TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
