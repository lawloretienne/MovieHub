package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.MovieDataSourceContract;
import com.etiennelawlor.moviehub.domain.mappers.MoviesDomainModelMapper;
import com.etiennelawlor.moviehub.domain.models.MoviesDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class MoviesUseCase implements MoviesDomainContract.UseCase {

    // region Member Variables
    private final MovieDataSourceContract.Repository movieRepository;
    private final MoviesDomainModelMapper moviesDomainModelMapper = new MoviesDomainModelMapper();
    // endregion

    // region Constructors
    public MoviesUseCase(MovieDataSourceContract.Repository movieRepository) {
        this.movieRepository = movieRepository;
    }
    // endregion

    // region MoviesDomainContract.UseCase Methods
    @Override
    public Single<MoviesDomainModel> getPopularMovies(int currentPage) {
        return movieRepository.getPopularMovies(currentPage)
                .map(moviesDataModel -> moviesDomainModelMapper.mapToDomainModel(moviesDataModel));
                // Espresso should only show up in instrumented unit tests, in the androidTest/ directory
//                .doOnSubscribe(disposable1 -> {
//                    // The network request might be handled in a different thread so make sure Espresso knows
//                    // that the app is busy until the response is handled.
//                    EspressoIdlingResource.increment(); // App is busy until further notice
//                })
//                .doFinally(() -> {
//                    // https://github.com/VisheshVadhera/PlacementApp/blob/f36e8c259cbba37c1be90409016854f8c64bb8a5/app/src/main/java/com/vishesh/placement/core/useCases/BaseUseCase.java
//                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
//                        EspressoIdlingResource.decrement(); // Set app as idle.
//                    }
//                })
    }
    // endregion
}
