package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRepository;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;
import com.etiennelawlor.moviehub.domain.TelevisionShowsUseCase;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsPresenter;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class TelevisionShowsModule {

    private TelevisionShowsUiContract.View televisionShowsView;

    public TelevisionShowsModule(TelevisionShowsUiContract.View televisionShowsView) {
        this.televisionShowsView = televisionShowsView;
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
    public TelevisionShowRepository provideMovieRepository(TelevisionShowLocalDataSource televisionShowLocalDataSource, TelevisionShowRemoteDataSource televisionShowRemoteDataSource) {
        return new TelevisionShowRepository(televisionShowLocalDataSource, televisionShowRemoteDataSource);
    }

    @Provides
    public ProductionSchedulerTransformer<TelevisionShowsPage> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<TelevisionShowsPage>();
    }

    @Provides
    public TelevisionShowsUseCase provideTelevisionShowsUseCase(TelevisionShowRepository televisionShowRepository, ProductionSchedulerTransformer<TelevisionShowsPage> productionSchedulerTransformer) {
        return new TelevisionShowsUseCase(televisionShowRepository, productionSchedulerTransformer);
    }

    @Provides
    public TelevisionShowsPresenter provideTelevisionShowsPresenter(TelevisionShowsUseCase televisionShowsUseCase) {
        return new TelevisionShowsPresenter(televisionShowsView, televisionShowsUseCase);
    }
}
