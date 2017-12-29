package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 11/11/17.
 */

public class TelevisionShowsUseCase implements TelevisionShowsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    // endregion

    // region Constructors
    public TelevisionShowsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository) {
        this.televisionShowRepository = televisionShowRepository;
    }
    // endregion

    // region TelevisionShowsDomainContract.UseCase Methods
    @Override
    public Single<TelevisionShowsPage> getPopularTelevisionShows(int currentPage) {
        return televisionShowRepository.getPopularTelevisionShows(currentPage);
                // .map( transform response to domainmodel) // create mapper class to make this cleaner and write unit tests for the mapper
    }

    // public Single<<X>DomainModel> getPopularTelevisionShows(){

    // }

    // Execute repository call i get response.  map response -> domainmodel. pass domainmodel to presenter.
    // DisposableSingleObserver has to be DisposableSingleObserver<<X>DomainModel>

    // endregion
}
