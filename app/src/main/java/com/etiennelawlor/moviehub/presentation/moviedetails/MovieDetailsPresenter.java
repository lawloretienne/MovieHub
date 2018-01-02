package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenter implements MovieDetailsUiContract.Presenter {

    // region Member Variables
    private final MovieDetailsUiContract.View movieDetailsView;
    private final MovieDetailsDomainContract.UseCase movieDetailsUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public MovieDetailsPresenter(MovieDetailsUiContract.View movieDetailsView, MovieDetailsDomainContract.UseCase movieDetailsUseCase) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsUseCase = movieDetailsUseCase;
    }
    // endregion

    // region MovieDetailsUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadMovieDetails(int movieId) {
        Disposable disposable = movieDetailsUseCase.getMovieDetails(movieId)
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<MovieDetailsDomainModel>())
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
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                            movieDetailsView.showErrorView();
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonDomainModel person) {
        movieDetailsView.openPersonDetails(person);
    }

    @Override
    public void onMovieClick(MovieDomainModel movie) {
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
