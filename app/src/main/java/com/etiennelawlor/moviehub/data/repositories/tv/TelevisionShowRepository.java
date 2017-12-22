package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;

import io.reactivex.Maybe;
import io.reactivex.Single;

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
    public Single<TelevisionShowsPage> getPopularTelevisionShows(final int currentPage) {
        Maybe<TelevisionShowsPage> local = televisionShowLocalDataSource.getPopularTelevisionShows(currentPage)
                .filter(televisionShowsPage -> !televisionShowsPage.isExpired());
        Single<TelevisionShowsPage> remote =
                televisionShowRemoteDataSource.getPopularTelevisionShows(currentPage)
                        .doOnSuccess(televisionShowsPage -> televisionShowLocalDataSource.savePopularTelevisionShows(televisionShowsPage));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int tvId) {
        Maybe<TelevisionShowDetailsWrapper> local = televisionShowLocalDataSource.getTelevisionShowDetails(tvId);
        Single<TelevisionShowDetailsWrapper> remote =
                televisionShowRemoteDataSource.getTelevisionShowDetails(tvId)
                        .doOnSuccess(televisionShowDetailsWrapper -> televisionShowLocalDataSource.saveTelevisionShowDetails(televisionShowDetailsWrapper));

        return local.switchIfEmpty(remote);
    }
    // endregion
}
