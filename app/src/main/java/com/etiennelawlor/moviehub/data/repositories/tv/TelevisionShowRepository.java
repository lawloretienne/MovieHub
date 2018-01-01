package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsResponse;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowCreditsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
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
    private TelevisionShowDataModelMapper televisionShowDataModelMapper = new TelevisionShowDataModelMapper();
    private TelevisionShowCreditsDataModelMapper televisionShowCreditsDataModelMapper = new TelevisionShowCreditsDataModelMapper();
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
    public Single<TelevisionShowDataModel> getTelevisionShow(int tvId) {
        Maybe<TelevisionShowDataModel> local = televisionShowLocalDataSource.getTelevisionShow(tvId);
        Single<TelevisionShowDataModel> remote =
                televisionShowRemoteDataSource.getTelevisionShow(tvId)
                        .map(televisionShowResponse -> televisionShowDataModelMapper.mapToDataModel(televisionShowResponse))
                        .doOnSuccess(televisionShowDataModel -> televisionShowLocalDataSource.saveTelevisionShow(televisionShowDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowCreditsDataModel> getTelevisionShowCredits(int tvId) {
        Maybe<TelevisionShowCreditsDataModel> local = televisionShowLocalDataSource.getTelevisionShowCredits(tvId);
        Single<TelevisionShowCreditsDataModel> remote =
                televisionShowRemoteDataSource.getTelevisionShowCredits(tvId)
                        .map(televisionShowCreditsResponse -> televisionShowCreditsDataModelMapper.mapToDataModel(televisionShowCreditsResponse))
                        .doOnSuccess(televisionShowCreditsDataModel -> televisionShowLocalDataSource.saveTelevisionShowCredits(televisionShowCreditsDataModel));

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
