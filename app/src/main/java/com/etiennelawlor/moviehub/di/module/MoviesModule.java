package com.etiennelawlor.moviehub.di.module;

import android.content.Context;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRepository;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;
import com.etiennelawlor.moviehub.domain.MoviesUseCase;
import com.etiennelawlor.moviehub.presentation.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.presentation.movies.MoviesUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class MoviesModule {

    @Provides
    @Singleton
    public MovieLocalDataSource provideMovieLocalDataSource(Context context) {
        return new MovieLocalDataSource(context);
    }

    @Provides
    @Singleton
    public MovieRemoteDataSource provideMovieRemoteDataSource(Context context) {
        return new MovieRemoteDataSource(context);
    }

    @Provides
    @Singleton
    public MovieRepository provideMovieRepository(MovieLocalDataSource movieLocalDataSource, MovieRemoteDataSource movieRemoteDataSource) {
        return new MovieRepository(movieLocalDataSource, movieRemoteDataSource);
    }

    @Provides
    @Singleton
    public ProductionSchedulerTransformer provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<MoviesPage>();
    }

    @Provides
    @Singleton
    public MoviesUseCase provideMoviesUseCase(MovieRepository movieRepository, ProductionSchedulerTransformer productionSchedulerTransformer) {
        return new MoviesUseCase(movieRepository, productionSchedulerTransformer);
    }

    @Provides
    @Singleton
    public MoviesPresenter provideMoviesPresenter(MoviesUiContract.View moviesView, MoviesUseCase moviesUseCase) {
        return new MoviesPresenter(moviesView, moviesUseCase);
    }
}
