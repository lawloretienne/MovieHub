package com.etiennelawlor.moviehub.domain.usecases;

import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.domain.composers.TelevisionShowDetailsDomainModelComposer;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class TelevisionShowDetailsUseCase implements TelevisionShowDetailsDomainContract.UseCase {

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private final TelevisionShowDetailsDomainModelComposer televisionShowDetailsDomainModelComposer = new TelevisionShowDetailsDomainModelComposer();
    // endregion

    // region Constructors
    public TelevisionShowDetailsUseCase(TelevisionShowDataSourceContract.Repository televisionShowRepository) {
        this.televisionShowRepository = televisionShowRepository;
    }
    // endregion

    // region MovieDetailsDomainContract.UseCase Methods
    @Override
    public Single<TelevisionShowDetailsDomainModel> getTelevisionShowDetails(int televisionShowId) {
        return Single.zip(
                televisionShowRepository.getTelevisionShow(televisionShowId),
                televisionShowRepository.getTelevisionShowCredits(televisionShowId),
                televisionShowRepository.getSimilarTelevisionShows(televisionShowId),
                televisionShowRepository.getTelevisionShowContentRatings(televisionShowId),
                (televisionShowDataModel, televisionShowCreditsDataModel, televisionShowsDataModel, televisionShowContentRatingsDataModel) ->
                    televisionShowDetailsDomainModelComposer.compose(televisionShowDataModel, televisionShowCreditsDataModel, televisionShowsDataModel, televisionShowContentRatingsDataModel));
    }
    // endregion

}
