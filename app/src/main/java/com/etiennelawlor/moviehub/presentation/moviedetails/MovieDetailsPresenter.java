package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.domain.usecases.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.MovieDetailsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.MovieDetailsPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

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
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MovieDetailsPresentationModelMapper movieDetailsPresentationModelMapper = new MovieDetailsPresentationModelMapper();
    // endregion

    // region Constructors
    public MovieDetailsPresenter(MovieDetailsPresentationContract.View movieDetailsView, MovieDetailsDomainContract.UseCase movieDetailsUseCase) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsUseCase = movieDetailsUseCase;
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
                .map(movieDetailsDomainModel -> movieDetailsPresentationModelMapper.mapToPresentationModel(movieDetailsDomainModel))
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<MovieDetailsPresentationModel>())
                .subscribeWith(new DisposableSingleObserver<MovieDetailsPresentationModel>() {
                    @Override
                    public void onSuccess(MovieDetailsPresentationModel movieDetailsPresentationModel) {
                        if(movieDetailsPresentationModel != null){
                            movieDetailsView.showMovieDetails(movieDetailsPresentationModel);
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
