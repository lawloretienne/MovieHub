package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDateResponse;
import com.etiennelawlor.moviehub.data.network.response.ReleaseDateResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDateDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.ReleaseDateDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDateDataModelMapper implements DataModelMapper<MovieReleaseDateResponse, MovieReleaseDateDataModel> {

    // region Member Variables
    private ReleaseDateDataModelMapper releaseDateDataModelMapper = new ReleaseDateDataModelMapper();
    // endregion

    @Override
    public MovieReleaseDateDataModel mapToDataModel(MovieReleaseDateResponse movieReleaseDateResponse) {
        MovieReleaseDateDataModel movieReleaseDateDataModel = new MovieReleaseDateDataModel();
        movieReleaseDateDataModel.setIso31661(movieReleaseDateResponse.getIso31661());

        List<ReleaseDateResponse> releaseDateResponses = movieReleaseDateResponse.getReleaseDates();
        List<ReleaseDateDataModel> releaseDateDataModels = new ArrayList<>();
        if(releaseDateResponses != null && releaseDateResponses.size()>0) {
            for (ReleaseDateResponse releaseDateResponse : releaseDateResponses) {
                releaseDateDataModels.add(releaseDateDataModelMapper.mapToDataModel(releaseDateResponse));
            }
        }
        movieReleaseDateDataModel.setReleaseDates(releaseDateDataModels);

        return movieReleaseDateDataModel;
    }
}
