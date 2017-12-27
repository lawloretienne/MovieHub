package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.search.SearchLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.search.SearchRepository;
import com.etiennelawlor.moviehub.data.repositories.search.models.SearchWrapper;
import com.etiennelawlor.moviehub.domain.SearchUseCase;
import com.etiennelawlor.moviehub.presentation.search.SearchPresenter;
import com.etiennelawlor.moviehub.presentation.search.SearchUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

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
    public SearchLocalDataSource provideSearchLocalDataSource() {
        return new SearchLocalDataSource();
    }

    @Provides
    public SearchRemoteDataSource provideSearchRemoteDataSource() {
        return new SearchRemoteDataSource();
    }

    @Provides
    public SearchRepository provideMovieRepository(SearchLocalDataSource searchLocalDataSource, SearchRemoteDataSource searchRemoteDataSource) {
        return new SearchRepository(searchLocalDataSource, searchRemoteDataSource);
    }

    @Provides
    public ProductionSchedulerTransformer<SearchWrapper> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<SearchWrapper>();
    }

    @Provides
    public SearchUseCase provideMoviesUseCase(SearchRepository searchRepository, ProductionSchedulerTransformer<SearchWrapper> productionSchedulerTransformer) {
        return new SearchUseCase(searchRepository, productionSchedulerTransformer);
    }

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new ProductionSchedulerProvider();
    }

    @Provides
    public SearchPresenter provideMoviesPresenter(SearchUseCase searchUseCase, SchedulerProvider schedulerProvider) {
        return new SearchPresenter(searchView, searchUseCase, schedulerProvider);
    }
}
