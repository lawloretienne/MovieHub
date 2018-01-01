package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsResponse;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowRepository implements TelevisionShowDataSourceContract.Repository {

    // region Member Variables
    private TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource;
    private TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource;
    private TelevisionShowsDataModelMapper televisionShowsDataModelMapper = new TelevisionShowsDataModelMapper();
    // endregion

    // region Constructors
    public TelevisionShowRepository(TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource, TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource) {
        this.televisionShowLocalDataSource = televisionShowLocalDataSource;
        this.televisionShowRemoteDataSource = televisionShowRemoteDataSource;
    }
    // endregion

    // region TelevisionShowDataSourceContract.Repository Methods
    @Override
    public Single<TelevisionShowsDataModel> getPopularTelevisionShows(final int currentPage) {
        Maybe<TelevisionShowsDataModel> local = televisionShowLocalDataSource.getPopularTelevisionShows(currentPage)
                .filter(televisionShowsDataModel -> !televisionShowsDataModel.isExpired());
        Single<TelevisionShowsDataModel> remote =
                televisionShowRemoteDataSource.getPopularTelevisionShows(currentPage)
                        .map(televisionShowsEnvelope -> televisionShowsDataModelMapper.mapToDataModel(televisionShowsEnvelope))
                        .doOnSuccess(televisionShowsDataModel -> televisionShowLocalDataSource.savePopularTelevisionShows(televisionShowsDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowResponse> getTelevisionShow(int tvId) {
        Maybe<TelevisionShowResponse> local = televisionShowLocalDataSource.getTelevisionShow(tvId);
        Single<TelevisionShowResponse> remote =
                televisionShowRemoteDataSource.getTelevisionShow(tvId)
                        .doOnSuccess(televisionShow -> televisionShowLocalDataSource.saveTelevisionShow(televisionShow));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowCreditsResponse> getTelevisionShowCredits(int tvId) {
        Maybe<TelevisionShowCreditsResponse> local = televisionShowLocalDataSource.getTelevisionShowCredits(tvId);
        Single<TelevisionShowCreditsResponse> remote =
                televisionShowRemoteDataSource.getTelevisionShowCredits(tvId)
                        .doOnSuccess(televisionShowCreditsEnvelope -> televisionShowLocalDataSource.saveTelevisionShowCredits(televisionShowCreditsEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowsDataModel> getSimilarTelevisionShows(int tvId) {
        Maybe<TelevisionShowsDataModel> local = televisionShowLocalDataSource.getSimilarTelevisionShows(tvId);
        Single<TelevisionShowsDataModel> remote =
                televisionShowRemoteDataSource.getSimilarTelevisionShows(tvId)
                        .map(televisionShowsEnvelope -> televisionShowsDataModelMapper.mapToDataModel(televisionShowsEnvelope))
                        .doOnSuccess(televisionShowsDataModel -> televisionShowLocalDataSource.saveSimilarTelevisionShows(televisionShowsDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowContentRatingsResponse> getTelevisionShowContentRatings(int tvId) {
        Maybe<TelevisionShowContentRatingsResponse> local = televisionShowLocalDataSource.getTelevisionShowContentRatings(tvId);
        Single<TelevisionShowContentRatingsResponse> remote =
                televisionShowRemoteDataSource.getTelevisionShowContentRatings(tvId)
                        .doOnSuccess(televisionShowContentRatingsEnvelope -> televisionShowLocalDataSource.saveTelevisionShowContentRatings(televisionShowContentRatingsEnvelope));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
