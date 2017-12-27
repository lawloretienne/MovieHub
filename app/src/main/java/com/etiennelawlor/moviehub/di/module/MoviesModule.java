package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRepository;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;
import com.etiennelawlor.moviehub.domain.MoviesUseCase;
import com.etiennelawlor.moviehub.presentation.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.presentation.movies.MoviesUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class MoviesModule {

    private MoviesUiContract.View moviesView;

    public MoviesModule(MoviesUiContract.View moviesView) {
        this.moviesView = moviesView;
    }

    @Provides
    public MovieLocalDataSource provideMovieLocalDataSource() {
        return new MovieLocalDataSource();
    }

    @Provides
    public MovieRemoteDataSource provideMovieRemoteDataSource() {
        return new MovieRemoteDataSource();
    }

    @Provides
    public MovieRepository provideMovieRepository(MovieLocalDataSource movieLocalDataSource, MovieRemoteDataSource movieRemoteDataSource) {
        return new MovieRepository(movieLocalDataSource, movieRemoteDataSource);
    }

    @Provides
    public ProductionSchedulerTransformer<MoviesPage> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<MoviesPage>();
    }

    @Provides
    public MoviesUseCase provideMoviesUseCase(MovieRepository movieRepository, ProductionSchedulerTransformer<MoviesPage> productionSchedulerTransformer) {
        return new MoviesUseCase(movieRepository, productionSchedulerTransformer);
    }

    @Provides
    public MoviesPresenter provideMoviesPresenter(MoviesUseCase moviesUseCase) {
        return new MoviesPresenter(moviesView, moviesUseCase);
    }
}
