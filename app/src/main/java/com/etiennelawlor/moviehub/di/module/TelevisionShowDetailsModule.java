package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRepository;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.domain.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.TelevisionShowDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class TelevisionShowDetailsModule {

    private TelevisionShowDetailsUiContract.View televisionShowDetailsView;

    public TelevisionShowDetailsModule(TelevisionShowDetailsUiContract.View televisionShowDetailsView) {
        this.televisionShowDetailsView = televisionShowDetailsView;
    }

    @Provides
    public TelevisionShowDataSourceContract.LocalDateSource provideTelevisionShowLocalDataSource() {
        return new TelevisionShowLocalDataSource();
    }

    @Provides
    public TelevisionShowDataSourceContract.RemoteDateSource provideTelevisionShowRemoteDataSource(MovieHubService movieHubService) {
        return new TelevisionShowRemoteDataSource(movieHubService);
    }

    @Provides
    public TelevisionShowDataSourceContract.Repository provideTelevisionShowRepository(TelevisionShowDataSourceContract.LocalDateSource televisionShowLocalDataSource, TelevisionShowDataSourceContract.RemoteDateSource televisionShowRemoteDataSource) {
        return new TelevisionShowRepository(televisionShowLocalDataSource, televisionShowRemoteDataSource);
    }

    @Provides
    public SchedulerTransformer<TelevisionShowDetailsWrapper> proviedSchedulerTransformer() {
        return new ProductionSchedulerTransformer<TelevisionShowDetailsWrapper>();
    }

    @Provides
    public TelevisionShowDetailsDomainContract.UseCase provideTelevisionShowDetailsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository, SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer) {
        return new TelevisionShowDetailsUseCase(televisionShowRepository, schedulerTransformer);
    }

    @Provides
    public TelevisionShowDetailsUiContract.Presenter provideTelevisionShowDetailsPresenter(TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase) {
        return new TelevisionShowDetailsPresenter(televisionShowDetailsView, televisionShowDetailsUseCase);
    }
}
