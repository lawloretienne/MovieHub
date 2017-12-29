package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRepository;
import com.etiennelawlor.moviehub.domain.TelevisionShowsDomainContract;
import com.etiennelawlor.moviehub.domain.TelevisionShowsUseCase;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsPresenter;
import com.etiennelawlor.moviehub.presentation.televisionshows.TelevisionShowsUiContract;

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
    public TelevisionShowsDomainContract.UseCase provideTelevisionShowsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository) {
        return new TelevisionShowsUseCase(televisionShowRepository);
    }

    @Provides
    public TelevisionShowsUiContract.Presenter provideTelevisionShowsPresenter(TelevisionShowsDomainContract.UseCase televisionShowsUseCase) {
        return new TelevisionShowsPresenter(televisionShowsView, televisionShowsUseCase);
    }
}
