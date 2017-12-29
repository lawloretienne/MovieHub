package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRepository;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.domain.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.MovieDetailsUseCase;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsPresenter;
import com.etiennelawlor.moviehub.presentation.moviedetails.MovieDetailsUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

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
    public MovieDataSourceContract.LocalDateSource provideMovieLocalDataSource() {
        return new MovieLocalDataSource();
    }

    @Provides
    public MovieDataSourceContract.RemoteDateSource provideMovieRemoteDataSource(MovieHubService movieHubService) {
        return new MovieRemoteDataSource(movieHubService);
    }

    @Provides
    public MovieDataSourceContract.Repository provideMovieRepository(MovieDataSourceContract.LocalDateSource movieLocalDataSource, MovieDataSourceContract.RemoteDateSource movieRemoteDataSource) {
        return new MovieRepository(movieLocalDataSource, movieRemoteDataSource);
    }

    @Provides
    public SchedulerTransformer<MovieDetailsWrapper> provideSchedulerTransformer() {
        return new ProductionSchedulerTransformer<MovieDetailsWrapper>();
    }

    @Provides
    public MovieDetailsDomainContract.UseCase provideMovieDetailsUseCase(MovieDataSourceContract.Repository movieRepository, SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer) {
        return new MovieDetailsUseCase(movieRepository, schedulerTransformer);
    }

    @Provides
    public MovieDetailsUiContract.Presenter provideMovieDetailsPresenter(MovieDetailsDomainContract.UseCase movieDetailsUseCase) {
        return new MovieDetailsPresenter(movieDetailsView, movieDetailsUseCase);
    }
}
