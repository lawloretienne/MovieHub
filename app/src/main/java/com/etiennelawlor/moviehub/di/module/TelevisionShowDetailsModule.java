package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRepository;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.domain.TelevisionShowDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

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
    public TelevisionShowLocalDataSource provideTelevisionShowLocalDataSource() {
        return new TelevisionShowLocalDataSource();
    }

    @Provides
    public TelevisionShowRemoteDataSource provideTelevisionShowRemoteDataSource() {
        return new TelevisionShowRemoteDataSource();
    }

    @Provides
    public TelevisionShowRepository provideTelevisionShowRepository(TelevisionShowLocalDataSource televisionShowLocalDataSource, TelevisionShowRemoteDataSource televisionShowRemoteDataSource) {
        return new TelevisionShowRepository(televisionShowLocalDataSource, televisionShowRemoteDataSource);
    }

    @Provides
    public ProductionSchedulerTransformer<TelevisionShowDetailsWrapper> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<TelevisionShowDetailsWrapper>();
    }

    @Provides
    public TelevisionShowDetailsUseCase provideMovieDetailsUseCase(TelevisionShowRepository televisionShowRepository, ProductionSchedulerTransformer<TelevisionShowDetailsWrapper> productionSchedulerTransformer) {
        return new TelevisionShowDetailsUseCase(televisionShowRepository, productionSchedulerTransformer);
    }

    @Provides
    public TelevisionShowDetailsPresenter provideTelevisionShowDetailsPresenter(TelevisionShowDetailsUseCase televisionShowDetailsUseCase) {
        return new TelevisionShowDetailsPresenter(televisionShowDetailsView, televisionShowDetailsUseCase);
    }
}
