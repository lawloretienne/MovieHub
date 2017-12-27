package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRepository;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.domain.MovieDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class MovieDetailsModule {

    private MovieDetailsUiContract.View movieDetailsView;

    public MovieDetailsModule(MovieDetailsUiContract.View movieDetailsView) {
        this.movieDetailsView = movieDetailsView;
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
    public ProductionSchedulerTransformer<MovieDetailsWrapper> provideProductionSchedulerTransformer() {
        return new ProductionSchedulerTransformer<MovieDetailsWrapper>();
    }

    @Provides
    public MovieDetailsUseCase provideMovieDetailsUseCase(MovieRepository movieRepository, ProductionSchedulerTransformer<MovieDetailsWrapper> productionSchedulerTransformer) {
        return new MovieDetailsUseCase(movieRepository, productionSchedulerTransformer);
    }

    @Provides
    public MovieDetailsPresenter provideMovieDetailsPresenter(MovieDetailsUseCase movieDetailsUseCase) {
        return new MovieDetailsPresenter(movieDetailsView, movieDetailsUseCase);
    }
}
