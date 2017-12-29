package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRepository;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;
import com.etiennelawlor.moviehub.domain.MoviesDomainContract;
import com.etiennelawlor.moviehub.domain.MoviesUseCase;
import com.etiennelawlor.moviehub.presentation.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.presentation.movies.MoviesUiContract;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

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
    public SchedulerTransformer<MoviesPage> provideSchedulerTransformer() {
        return new ProductionSchedulerTransformer<MoviesPage>();
    }

    @Provides
    public MoviesDomainContract.UseCase provideMoviesUseCase(MovieDataSourceContract.Repository movieRepository, SchedulerTransformer<MoviesPage> schedulerTransformer) {
        return new MoviesUseCase(movieRepository, schedulerTransformer);
    }

    @Provides
    public MoviesUiContract.Presenter provideMoviesPresenter(MoviesDomainContract.UseCase moviesUseCase) {
        return new MoviesPresenter(moviesView, moviesUseCase);
    }
}
