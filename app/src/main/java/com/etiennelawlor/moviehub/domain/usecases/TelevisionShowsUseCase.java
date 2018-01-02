package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.domain.mappers.TelevisionShowsDomainModelMapper;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 11/11/17.
 */

public class TelevisionShowsUseCase implements TelevisionShowsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private TelevisionShowsDomainModelMapper televisionShowsDomainModelMapper = new TelevisionShowsDomainModelMapper();
    // endregion

    // region Constructors
    public TelevisionShowsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository) {
        this.televisionShowRepository = televisionShowRepository;
    }
    // endregion

    // region TelevisionShowsDomainContract.UseCase Methods
    @Override
    public Single<TelevisionShowsDomainModel> getPopularTelevisionShows(int currentPage) {
        return televisionShowRepository.getPopularTelevisionShows(currentPage)
                .map(televisionShowsDataModel -> televisionShowsDomainModelMapper.mapToDomainModel(televisionShowsDataModel));
                // write unit tests for the mapper
    }

    // endregion
}
