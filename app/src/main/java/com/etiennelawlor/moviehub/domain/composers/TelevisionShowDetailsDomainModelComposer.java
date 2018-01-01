package com.etiennelawlor.moviehub.domain.composers;

import com.etiennelawlor.moviehub.data.repositories.models.ContentRatingDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowContentRatingsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowCreditsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class TelevisionShowDetailsDomainModelComposer {

    // region Constants
    private static final String ISO_31661 = "US";
    // endregion

    public TelevisionShowDetailsDomainModel compose(TelevisionShowDataModel televisionShowDataModel, TelevisionShowCreditsDataModel televisionShowCreditsDataModel, TelevisionShowsDataModel televisionShowsDataModel, TelevisionShowContentRatingsDataModel televisionShowContentRatingsDataModel){
        TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel = new TelevisionShowDetailsDomainModel();

        List<TelevisionShowCreditDataModel> cast = new ArrayList<>();
        List<TelevisionShowCreditDataModel> crew = new ArrayList<>();
        List<TelevisionShowDataModel> similarTelevisionShows = new ArrayList<>();
        String rating = "";

        if(televisionShowCreditsDataModel!=null){
            cast = televisionShowCreditsDataModel.getCast();
        }

        if(televisionShowCreditsDataModel!=null){
            crew = televisionShowCreditsDataModel.getCrew();
        }

        if(televisionShowsDataModel!=null){
            similarTelevisionShows = televisionShowsDataModel.getTelevisionShows();
        }

        if(televisionShowContentRatingsDataModel!=null){
            List<ContentRatingDataModel> contentRatingDataModels = televisionShowContentRatingsDataModel.getContentRatings();
            if(contentRatingDataModels != null && contentRatingDataModels.size() > 0){
                for(ContentRatingDataModel contentRatingDataModel : contentRatingDataModels){
                    String iso31661 = contentRatingDataModel.getIso31661();
                    if(iso31661.equals(ISO_31661)){
                        rating = contentRatingDataModel.getRating();
                        break;
                    }
                }
            }
        }

        televisionShowDetailsDomainModel.setCast(cast);
        televisionShowDetailsDomainModel.setCrew(crew);
        televisionShowDetailsDomainModel.setRating(rating);
        televisionShowDetailsDomainModel.setSimilarTelevisionShows(similarTelevisionShows);
        televisionShowDetailsDomainModel.setTelevisionShow(televisionShowDataModel);

        return televisionShowDetailsDomainModel;
    }
}
