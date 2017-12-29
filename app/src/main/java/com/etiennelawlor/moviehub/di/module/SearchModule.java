package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.search.SearchDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.search.SearchLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRepository;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.domain.SearchDomainContract;
import com.etiennelawlor.moviehub.domain.SearchUseCase;
import com.etiennelawlor.moviehub.presentation.search.SearchPresenter;
import com.etiennelawlor.moviehub.presentation.search.SearchUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class SearchModule {

    private SearchUiContract.View searchView;

    public SearchModule(SearchUiContract.View searchView) {
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
    public SchedulerTransformer<SearchWrapper> provideSchedulerTransformer() {
        return new ProductionSchedulerTransformer<SearchWrapper>();
    }

    @Provides
    public SearchDomainContract.UseCase provideSearchUseCase(SearchDataSourceContract.Repository searchRepository, SchedulerTransformer<SearchWrapper> schedulerTransformer) {
        return new SearchUseCase(searchRepository, schedulerTransformer);
    }

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new ProductionSchedulerProvider();
    }

    @Provides
    public SearchUiContract.Presenter provideSearchPresenter(SearchDomainContract.UseCase searchUseCase, SchedulerProvider schedulerProvider) {
        return new SearchPresenter(searchView, searchUseCase, schedulerProvider);
    }
}
