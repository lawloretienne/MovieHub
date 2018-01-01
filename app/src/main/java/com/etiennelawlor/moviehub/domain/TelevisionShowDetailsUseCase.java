package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.network.response.ContentRatingResponse;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCreditResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public class TelevisionShowDetailsUseCase implements TelevisionShowDetailsDomainContract.UseCase {

    // region Constants
    private static final String ISO_31661 = "US";
    // endregion

    // region Member Variables
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
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
                (televisionShowDataModel, televisionShowCreditsResponse, televisionShowsDataModel, televisionShowContentRatingsResponse) -> {
                    List<TelevisionShowCreditResponse> cast = new ArrayList<>();
                    List<TelevisionShowCreditResponse> crew = new ArrayList<>();
                    List<TelevisionShowDataModel> similarTelevisionShows = new ArrayList<>();
                    String rating = "";

                    if(televisionShowCreditsResponse!=null){
                        cast = televisionShowCreditsResponse.getCast();
                    }

                    if(televisionShowCreditsResponse!=null){
                        crew = televisionShowCreditsResponse.getCrew();
                    }

                    if(televisionShowsDataModel!=null){
                        similarTelevisionShows = televisionShowsDataModel.getTelevisionShows();
                    }

                    if(televisionShowContentRatingsResponse!=null){
                        List<ContentRatingResponse> contentRatings = televisionShowContentRatingsResponse.getContentRatings();
                        if(contentRatings != null && contentRatings.size() > 0){
                            for(ContentRatingResponse contentRating : contentRatings){
                                String iso31661 = contentRating.getIso31661();
                                if(iso31661.equals(ISO_31661)){
                                    rating = contentRating.getRating();
                                    break;
                                }
                            }
                        }
                    }

                    return new TelevisionShowDetailsDomainModel(televisionShowDataModel, cast, crew, similarTelevisionShows, rating);
                });
    }
    // endregion

}
