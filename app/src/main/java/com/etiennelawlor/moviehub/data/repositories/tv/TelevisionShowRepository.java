package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import java.util.Calendar;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowRepository implements TelevisionShowDataSourceContract.Repository {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

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
    public Single<TelevisionShowsDataModel> getPopularTelevisionShows(final int currentPage) {
        Maybe<TelevisionShowsDataModel> local = televisionShowLocalDataSource.getPopularTelevisionShows(currentPage)
                .filter(televisionShowsDataModel -> !televisionShowsDataModel.isExpired());
        Single<TelevisionShowsDataModel> remote =
                televisionShowRemoteDataSource.getPopularTelevisionShows(currentPage)
                        .flatMap(televisionShowsEnvelope -> Single.just(televisionShowsEnvelope.getTelevisionShows()))
                        .map(televisionShows -> {
                            boolean isLastPage = televisionShows.size() < PAGE_SIZE ? true : false;
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DATE, SEVEN_DAYS);
                            return new TelevisionShowsDataModel(televisionShows, currentPage, isLastPage, calendar.getTime() );
                        })
                        .doOnSuccess(televisionShowsDataModel -> televisionShowLocalDataSource.savePopularTelevisionShows(televisionShowsDataModel));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShow> getTelevisionShow(int tvId) {
        Maybe<TelevisionShow> local = televisionShowLocalDataSource.getTelevisionShow(tvId);
        Single<TelevisionShow> remote =
                televisionShowRemoteDataSource.getTelevisionShow(tvId)
                        .doOnSuccess(televisionShow -> televisionShowLocalDataSource.saveTelevisionShow(televisionShow));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowCreditsEnvelope> getTelevisionShowCredits(int tvId) {
        Maybe<TelevisionShowCreditsEnvelope> local = televisionShowLocalDataSource.getTelevisionShowCredits(tvId);
        Single<TelevisionShowCreditsEnvelope> remote =
                televisionShowRemoteDataSource.getTelevisionShowCredits(tvId)
                        .doOnSuccess(televisionShowCreditsEnvelope -> televisionShowLocalDataSource.saveTelevisionShowCredits(televisionShowCreditsEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowsEnvelope> getSimilarTelevisionShows(int tvId) {
        Maybe<TelevisionShowsEnvelope> local = televisionShowLocalDataSource.getSimilarTelevisionShows(tvId);
        Single<TelevisionShowsEnvelope> remote =
                televisionShowRemoteDataSource.getSimilarTelevisionShows(tvId)
                        .doOnSuccess(televisionShowsEnvelope -> televisionShowLocalDataSource.saveSimilarTelevisionShows(televisionShowsEnvelope));

        return local.switchIfEmpty(remote);
    }

    @Override
    public Single<TelevisionShowContentRatingsEnvelope> getTelevisionShowContentRatings(int tvId) {
        Maybe<TelevisionShowContentRatingsEnvelope> local = televisionShowLocalDataSource.getTelevisionShowContentRatings(tvId);
        Single<TelevisionShowContentRatingsEnvelope> remote =
                televisionShowRemoteDataSource.getTelevisionShowContentRatings(tvId)
                        .doOnSuccess(televisionShowContentRatingsEnvelope -> televisionShowLocalDataSource.saveTelevisionShowContentRatings(televisionShowContentRatingsEnvelope));

        return local.switchIfEmpty(remote);
    }

    // endregion
}
