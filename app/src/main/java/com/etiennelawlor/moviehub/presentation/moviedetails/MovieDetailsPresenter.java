package com.etiennelawlor.moviehub.presentation.moviedetails;

import android.content.Context;
import android.text.TextUtils;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.domain.models.GenreDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.util.DateUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenter implements MovieDetailsPresentationContract.Presenter {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    // endregion

    @Inject
    Context context;

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
//                            movieDetailsView.setMovieDetailsDomainModel(movieDetailsDomainModel);

                            MovieDomainModel movie = movieDetailsDomainModel.getMovie();
                            if(movie != null){
                                String overview = movie.getOverview();
                                if(!TextUtils.isEmpty(overview)){
                                    movieDetailsView.showOverview(overview);
                                } else {
                                    movieDetailsView.showOverview(context.getString(R.string.not_available));
                                }

                                int runTime = movie.getRuntime();
                                if(runTime>0) {
                                    int hours = runTime/60;
                                    int minutes = runTime%60;
                                    if(hours>0){
                                        if(minutes>0){
                                            movieDetailsView.showDuration(String.format("%dh %dm", hours, minutes));
                                        } else {
                                            movieDetailsView.showDuration(String.format("%dh", hours));
                                        }
                                    } else {
                                        movieDetailsView.showDuration(String.format("%dm", minutes));
                                    }
                                } else {
                                    movieDetailsView.showDuration(context.getString(R.string.not_available));
                                }

                                List<GenreDomainModel> genres = movie.getGenres();
                                if(genres != null && genres.size()>0){
                                    StringBuilder stringBuilder = new StringBuilder("");

                                    for(int i=0; i<genres.size(); i++){
                                        GenreDomainModel genre = genres.get(i);
                                        stringBuilder.append(genre.getName());
                                        if(i!=genres.size()-1){
                                            stringBuilder.append(" | ");
                                        }
                                    }

                                    movieDetailsView.showGenres(stringBuilder.toString());
                                } else {
                                    movieDetailsView.showGenres(context.getString(R.string.not_available));
                                }

                                String status = movie.getStatus();
                                if(!TextUtils.isEmpty(status)){
                                    movieDetailsView.showStatus(status);
                                } else {
                                    movieDetailsView.showStatus(context.getString(R.string.not_available));
                                }

                                String releaseDate = movie.getReleaseDate();
                                if(!TextUtils.isEmpty(releaseDate)){
                                    Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);

                                    String month = DateUtility.getMonth(calendar.get(Calendar.MONTH));
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                                    int year = calendar.get(Calendar.YEAR);

                                    String formattedReleaseDate = String.format("%s %d, %d", month, day, year);

                                    movieDetailsView.showReleaseDate(formattedReleaseDate);
                                } else {
                                    movieDetailsView.showReleaseDate(context.getString(R.string.not_available));
                                }

                                int budget = movie.getBudget();
                                if(budget > 0){
                                    movieDetailsView.showBudget(String.format("$%s", NumberFormat.getNumberInstance(Locale.US).format(budget)));
                                } else {
                                    movieDetailsView.showBudget(context.getString(R.string.not_available));
                                }

                                long revenue = movie.getRevenue();
                                if(revenue > 0L){
                                    movieDetailsView.showRevenue(String.format("$%s", NumberFormat.getNumberInstance(Locale.US).format(revenue)));
                                } else {
                                    movieDetailsView.showRevenue(context.getString(R.string.not_available));
                                }
                            }

                            String rating = movieDetailsDomainModel.getRating();
                            if(!TextUtils.isEmpty(rating)){
                                movieDetailsView.showRating(rating);
                            } else {
                                movieDetailsView.hideRatingView();
                            }

                            movieDetailsView.showMovieDetailsBodyView();

                            movieDetailsView.setCast(movieDetailsDomainModel.getCast());
                            movieDetailsView.setCrew(movieDetailsDomainModel.getCrew());
                            movieDetailsView.setSimilarMovies(movieDetailsDomainModel.getSimilarMovies());
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        movieDetailsView.showErrorView();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonPresentationModel person) {
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
