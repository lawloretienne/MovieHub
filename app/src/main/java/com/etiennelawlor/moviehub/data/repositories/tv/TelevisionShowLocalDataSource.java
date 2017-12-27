package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.database.RealmUtility;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;

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
    public Maybe<TelevisionShowsPage> getPopularTelevisionShows(int currentPage) {
        TelevisionShowsPage televisionShowsPage = RealmUtility.getTelevisionShowsPage(currentPage);
        if(televisionShowsPage == null)
            return Maybe.empty();
        else
            return Maybe.just(televisionShowsPage);
    }

    @Override
    public void savePopularTelevisionShows(TelevisionShowsPage televisionShowsPage) {
        RealmUtility.saveTelevisionShowsPage(televisionShowsPage);
    }

    @Override
    public Maybe<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int televisionShowId) {
        //        Use mapper to convert from realm objects to POJOs
        return Maybe.empty();
    }

    @Override
    public void saveTelevisionShowDetails(TelevisionShowDetailsWrapper televisionShowDetailsWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
