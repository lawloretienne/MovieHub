package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowContentRatingsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowCreditsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.mappers.TelevisionShowsDataModelMapper;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowContentRatingsDataModel;
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
    private TelevisionShowContentRatingsDataModelMapper televisionShowContentRatingsDataModelMapper = new TelevisionShowContentRatingsDataModelMapper();
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
    public Single<TelevisionShowContentRatingsDataModel> getTelevisionShowContentRatings(int tvId) {
        Maybe<TelevisionShowContentRatingsDataModel> local = televisionShowLocalDataSource.getTelevisionShowContentRatings(tvId);
        Single<TelevisionShowContentRatingsDataModel> remote =
                televisionShowRemoteDataSource.getTelevisionShowContentRatings(tvId)
                        .map(televisionShowContentRatingsResponse -> televisionShowContentRatingsDataModelMapper.mapToDataModel(televisionShowContentRatingsResponse))
                        .doOnSuccess(televisionShowContentRatingsDataModel -> televisionShowLocalDataSource.saveTelevisionShowContentRatings(televisionShowContentRatingsDataModel));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
