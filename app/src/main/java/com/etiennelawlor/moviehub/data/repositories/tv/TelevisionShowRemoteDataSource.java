package com.etiennelawlor.moviehub.data.repositories.tv;

import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.response.ContentRating;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowCredit;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShowsEnvelope;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowRemoteDataSource implements TelevisionShowDataSourceContract.RemoteDateSource {

    // region Constants
    private static final String ISO_31661 = "US";
    // endregion

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    @Inject
    public TelevisionShowRemoteDataSource(MovieHubService movieHubService) {
        this.movieHubService = movieHubService;
    }
    // endregion

    // region TelevisionShowDataSourceContract.RemoteDateSource Methods
    @Override
    public Single<TelevisionShowsEnvelope> getPopularTelevisionShows(int currentPage) {
        return movieHubService.getPopularTelevisionShows(currentPage);
    }

    @Override
    public Single<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int tvId) {
        return Single.zip(
                movieHubService.getTelevisionShow(tvId),
                movieHubService.getTelevisionShowCredits(tvId),
                movieHubService.getSimilarTelevisionShows(tvId),
                movieHubService.getTelevisionShowContentRatings(tvId),
                (televisionShow, televisionShowCreditsEnvelope, televisionShowsEnvelope, televisionShowContentRatingsEnvelope) -> {
                    List<TelevisionShowCredit> cast = new ArrayList<>();
                    List<TelevisionShowCredit> crew = new ArrayList<>();
                    List<TelevisionShow> similarTelevisionShows = new ArrayList<>();
                    String rating = "";

                    if(televisionShowCreditsEnvelope!=null){
                        cast = televisionShowCreditsEnvelope.getCast();
                    }

                    if(televisionShowCreditsEnvelope!=null){
                        crew = televisionShowCreditsEnvelope.getCrew();
                    }

                    if(televisionShowsEnvelope!=null){
                        similarTelevisionShows = televisionShowsEnvelope.getTelevisionShows();
                    }

                    if(televisionShowContentRatingsEnvelope!=null){
                        List<ContentRating> contentRatings = televisionShowContentRatingsEnvelope.getContentRatings();
                        if(contentRatings != null && contentRatings.size() > 0){
                            for(ContentRating contentRating : contentRatings){
                                String iso31661 = contentRating.getIso31661();
                                if(iso31661.equals(ISO_31661)){
                                    rating = contentRating.getRating();
                                    break;
                                }
                            }
                        }
                    }

                    return new TelevisionShowDetailsWrapper(televisionShow, cast, crew, similarTelevisionShows, rating);
                });
    }


    // endregion
}
