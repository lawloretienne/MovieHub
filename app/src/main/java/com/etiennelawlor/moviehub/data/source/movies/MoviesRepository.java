package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.viewmodel.MoviesViewModel;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRepository implements MoviesDataContract.Repository {

    // region Member Variables
    private MoviesDataContract.LocalDateSource moviesLocalDataSource;
    private MoviesDataContract.RemoteDateSource moviesRemoteDataSource;
    // endregion

    // region Constructors
    // Additionally i need to pass in configRemoteDataSource as
    public MoviesRepository(MoviesDataContract.LocalDateSource moviesLocalDataSource, MoviesDataContract.RemoteDateSource moviesRemoteDataSource) {
        this.moviesLocalDataSource = moviesLocalDataSource;
        this.moviesRemoteDataSource = moviesRemoteDataSource;
    }
    // endregion

    // region MoviesDataContract Methods




    @Override
    public Observable<MoviesViewModel> getPopularMovies(int currentPage) {
        // Observable.merge(local, remote (which saves to local))
        return moviesRemoteDataSource.getPopularMovies(currentPage);
        // Do the mapping and getting config info and put that into a viewmodel to be returned
        // 1. RemoteMove.getMovies()
        // 2. RemoteConfig.getConfig()
        // 3. combineLatest create ViewModel
        // 4. Persist the ViewModel in moviesLocalDataSource
        // 5. return ViewModel


        //                .map(new Func1<MoviesEnvelope, MoviesViewModel>() {
//                    @Override
//                    public MoviesViewModel call(MoviesEnvelope moviesEnvelope) {
//                        List<Movie> movies = moviesEnvelope.getMovies();
//                        int currentPage = moviesEnvelope.getPage();
//                        boolean isLastPage = moviesEnvelope.getMovies().size() < PAGE_SIZE ? true : false;
//
//                        return new MoviesViewModel(movies, currentPage, isLastPage);
//                    }
//                }).doOnNext(new Action1<MoviesViewModel>() {
//                    @Override
//                    public void call(MoviesViewModel moviesViewModel) {
//                        // todo: update realm
//                    }
//                });


//        return moviesRemoteDataSource.getPopularMovies(currentPage);  Do this instead
    }

    // endregion
}
