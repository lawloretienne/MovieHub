package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenter implements MovieDetailsPresentationContract.Presenter {

    // region Member Variables
    private final MovieDetailsPresentationContract.View movieDetailsView;
    private final MovieDetailsDomainContract.UseCase movieDetailsUseCase;
    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public MovieDetailsPresenter(MovieDetailsPresentationContract.View movieDetailsView, MovieDetailsDomainContract.UseCase movieDetailsUseCase, SchedulerProvider schedulerProvider) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsUseCase = movieDetailsUseCase;
        this.schedulerProvider = schedulerProvider;
    }
    // endregion

    // region MovieDetailsPresentationContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadMovieDetails(int movieId) {
        Disposable disposable = movieDetailsUseCase.getMovieDetails(movieId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<MovieDetailsDomainModel>() {
                    @Override
                    public void onSuccess(MovieDetailsDomainModel movieDetailsDomainModel) {
                        if(movieDetailsDomainModel != null){
                            movieDetailsView.showMovieDetails(movieDetailsDomainModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(NetworkUtility.isKnownException(throwable)){
                            movieDetailsView.showErrorView();
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonPresentationModel person) {
        movieDetailsView.openPersonDetails(person);
    }

    @Override
    public void onMovieClick(MoviePresentationModel movie) {
        movieDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            movieDetailsView.showToolbarTitle();
        else
            movieDetailsView.hideToolbarTitle();
    }
    // endregion
}
