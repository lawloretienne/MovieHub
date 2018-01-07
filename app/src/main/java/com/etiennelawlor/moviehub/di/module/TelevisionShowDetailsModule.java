package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowRepository;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresentationContract;
import com.etiennelawlor.moviehub.presentation.televisionshowdetails.TelevisionShowDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class TelevisionShowDetailsModule {

    private TelevisionShowDetailsPresentationContract.View televisionShowDetailsView;

    public TelevisionShowDetailsModule(TelevisionShowDetailsPresentationContract.View televisionShowDetailsView) {
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

//    @Provides
//    public SchedulerTransformer<TelevisionShowDetailsPresentationModel> proviedSchedulerTransformer() {
//        return new ProductionSchedulerTransformer<TelevisionShowDetailsPresentationModel>();
//    }

    @Provides
    public TelevisionShowDetailsDomainContract.UseCase provideTelevisionShowDetailsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository) {
        return new TelevisionShowDetailsUseCase(televisionShowRepository);
    }

    @Provides
    public TelevisionShowDetailsPresentationContract.Presenter provideTelevisionShowDetailsPresenter(TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase) {
        return new TelevisionShowDetailsPresenter(televisionShowDetailsView, televisionShowDetailsUseCase);
    }
}
