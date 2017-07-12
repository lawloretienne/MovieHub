package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowRepository implements TelevisionShowDataSourceContract.Repository {

    // region Member Variables
    private TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource;
    private TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource;
    // endregion

    // region Constructors
    public TelevisionShowRepository(TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource, TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource) {
        this.televisionShowLocalDataSource = televisionShowLocalDataSource;
        this.televisionShowRemoteDataSource = televisionShowRemoteDataSource;
    }
    // endregion

    // region TelevisionShowDataSourceContract.Repository Methods
    @Override
    public Observable<TelevisionShowsPage> getPopularTelevisionShows(final int currentPage) {
        Observable<TelevisionShowsPage> local = televisionShowLocalDataSource.getPopularTelevisionShows(currentPage)
                .filter(televisionShowsPage -> !televisionShowsPage.isExpired());
        Observable<TelevisionShowsPage> remote =
                televisionShowRemoteDataSource.getPopularTelevisionShows(currentPage)
                        .doOnNext(televisionShowsPage -> televisionShowLocalDataSource.savePopularTelevisionShows(televisionShowsPage));

        return Observable.concat(local, remote).first();
    }

    @Override
    public Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int tvId) {
        Observable<TelevisionShowDetailsWrapper> local = televisionShowLocalDataSource.getTelevisionShowDetails(tvId);
        Observable<TelevisionShowDetailsWrapper> remote =
                televisionShowRemoteDataSource.getTelevisionShowDetails(tvId)
                        .doOnNext(televisionShowDetailsWrapper -> televisionShowLocalDataSource.saveTelevisionShowDetails(televisionShowDetailsWrapper));

        return Observable.concat(local, remote).first();
    }
    // endregion
}
