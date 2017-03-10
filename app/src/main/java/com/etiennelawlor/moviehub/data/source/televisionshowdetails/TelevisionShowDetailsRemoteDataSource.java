package com.etiennelawlor.moviehub.data.source.televisionshowdetails;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.ContentRating;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowCredit;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShowsEnvelope;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func4;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class TelevisionShowDetailsRemoteDataSource implements TelevisionShowDetailsDataSourceContract.RemoteDateSource {

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public TelevisionShowDetailsRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region TelevisionShowDetailsDataSourceContract.RemoteDateSource Methods
    @Override
    public Observable<TelevisionShowDetailsWrapper> getTelevisionShowDetails(int tvId) {
        return Observable.zip(
                movieHubService.getTelevisionShowDetails(tvId),
                movieHubService.getTelevisionShowCredits(tvId),
                movieHubService.getSimilarTelevisionShows(tvId),
                movieHubService.getTelevisionShowContentRatings(tvId),
                new Func4<TelevisionShow, TelevisionShowCreditsEnvelope, TelevisionShowsEnvelope, TelevisionShowContentRatingsEnvelope, TelevisionShowDetailsWrapper>() {
                    @Override
                    public TelevisionShowDetailsWrapper call(TelevisionShow televisionShow, TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope, TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope) {
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
                                    if(iso31661.equals("US")){
                                        rating = contentRating.getRating();
                                        break;
                                    }
                                }
                            }
                        }

                        return new TelevisionShowDetailsWrapper(televisionShow, cast, crew, similarTelevisionShows, rating);
                    }
                });
    }

    // endregion
}
