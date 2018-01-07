package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.search.SearchLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRepository;
import com.etiennelawlor.moviehub.domain.usecases.SearchDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.SearchUseCase;
import com.etiennelawlor.moviehub.presentation.search.SearchPresentationContract;
import com.etiennelawlor.moviehub.presentation.search.SearchPresenter;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class SearchModule {

    private SearchPresentationContract.View searchView;

    public SearchModule(SearchPresentationContract.View searchView) {
        this.searchView = searchView;
    }

    @Provides
    public SearchDataSourceContract.LocalDateSource provideSearchLocalDataSource() {
        return new SearchLocalDataSource();
    }

    @Provides
    public SearchDataSourceContract.RemoteDateSource provideSearchRemoteDataSource(MovieHubService movieHubService) {
        return new SearchRemoteDataSource(movieHubService);
    }

    @Provides
    public SearchDataSourceContract.Repository provideSearchRepository(SearchDataSourceContract.LocalDateSource searchLocalDataSource, SearchDataSourceContract.RemoteDateSource searchRemoteDataSource) {
        return new SearchRepository(searchLocalDataSource, searchRemoteDataSource);
    }

    @Provides
    public SearchDomainContract.UseCase provideSearchUseCase(SearchDataSourceContract.Repository searchRepository) {
        return new SearchUseCase(searchRepository);
    }

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new ProductionSchedulerProvider();
    }

    @Provides
    public SearchPresentationContract.Presenter provideSearchPresenter(SearchDomainContract.UseCase searchUseCase, SchedulerProvider schedulerProvider) {
        return new SearchPresenter(searchView, searchUseCase, schedulerProvider);
    }
}
