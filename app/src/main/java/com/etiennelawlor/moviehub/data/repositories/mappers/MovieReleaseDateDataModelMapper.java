package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDateResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDateDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDateDataModelMapper implements DataModelMapper<MovieReleaseDateResponse, MovieReleaseDateDataModel>, DataModelListMapper<MovieReleaseDateResponse, MovieReleaseDateDataModel> {

    // region Member Variables
    private ReleaseDateDataModelMapper releaseDateDataModelMapper = new ReleaseDateDataModelMapper();
    // endregion

    @Override
    public MovieReleaseDateDataModel mapToDataModel(MovieReleaseDateResponse movieReleaseDateResponse) {
        MovieReleaseDateDataModel movieReleaseDateDataModel = new MovieReleaseDateDataModel();
        movieReleaseDateDataModel.setIso31661(movieReleaseDateResponse.getIso31661());
        movieReleaseDateDataModel.setReleaseDates(releaseDateDataModelMapper.mapListToDataModelList(movieReleaseDateResponse.getReleaseDates()));
        return movieReleaseDateDataModel;
    }

    @Override
    public List<MovieReleaseDateDataModel> mapListToDataModelList(List<MovieReleaseDateResponse> movieReleaseDateResponses) {
        List<MovieReleaseDateDataModel> movieReleaseDateDataModels = new ArrayList<>();
        if(movieReleaseDateResponses != null && movieReleaseDateResponses.size()>0) {
            for (MovieReleaseDateResponse movieReleaseDateResponse : movieReleaseDateResponses) {
                movieReleaseDateDataModels.add(mapToDataModel(movieReleaseDateResponse));
            }
        }
        return movieReleaseDateDataModels;
    }
}
