package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class MovieDetailsUseCase implements MovieDetailsDomainContract.UseCase {

    // region Member Variables
    private final MovieDataSourceContract.Repository movieRepository;
    private final SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public MovieDetailsUseCase(MovieDataSourceContract.Repository movieRepository, SchedulerTransformer<MovieDetailsWrapper> schedulerTransformer) {
        this.movieRepository = movieRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public void clearDisposables() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    @Override
    public void getMovieDetails(int movieId, DisposableSingleObserver disposableSingleObserver) {
        Disposable disposable = movieRepository.getMovieDetails(movieId)
                .compose(schedulerTransformer)
                .subscribeWith(disposableSingleObserver);
        compositeDisposable.add(disposable);
    }
    // endregion

}
