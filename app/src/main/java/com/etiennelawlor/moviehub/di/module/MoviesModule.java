package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieLocalDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRemoteDataSource;
import com.etiennelawlor.moviehub.data.repositories.movie.MovieRepository;
import com.etiennelawlor.moviehub.domain.usecases.MoviesDomainContract;
import com.etiennelawlor.moviehub.domain.usecases.MoviesUseCase;
import com.etiennelawlor.moviehub.presentation.movies.MoviesPresenter;
import com.etiennelawlor.moviehub.presentation.movies.MoviesPresentationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 2/9/17.
 */

@Module
public class MoviesModule {

    private MoviesPresentationContract.View moviesView;

    public MoviesModule(MoviesPresentationContract.View moviesView) {
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
    public MoviesDomainContract.UseCase provideMoviesUseCase(MovieDataSourceContract.Repository movieRepository) {
        return new MoviesUseCase(movieRepository);
    }

    @Provides
    public MoviesPresentationContract.Presenter provideMoviesPresenter(MoviesDomainContract.UseCase moviesUseCase) {
        return new MoviesPresenter(moviesView, moviesUseCase);
    }
}
