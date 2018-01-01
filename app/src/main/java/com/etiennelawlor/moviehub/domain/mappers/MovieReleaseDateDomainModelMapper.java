package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.MovieReleaseDateResponse;
import com.etiennelawlor.moviehub.data.repositories.models.MovieReleaseDateDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieReleaseDateDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDateDomainModelMapper implements DomainModelMapper<MovieReleaseDateDataModel, MovieReleaseDateDomainModel>, DomainModelListMapper<MovieReleaseDateDataModel, MovieReleaseDateDomainModel> {

    // region Member Variables
    private ReleaseDateDomainModelMapper releaseDateDomainModelMapper = new ReleaseDateDomainModelMapper();
    // endregion

    @Override
    public MovieReleaseDateDomainModel mapToDomainModel(MovieReleaseDateDataModel movieReleaseDateDataModel) {
        MovieReleaseDateDomainModel movieReleaseDateDomainModel = new MovieReleaseDateDomainModel();
        movieReleaseDateDomainModel.setIso31661(movieReleaseDateDataModel.getIso31661());
        movieReleaseDateDomainModel.setReleaseDates(releaseDateDomainModelMapper.mapListToDomainModelList(movieReleaseDateDataModel.getReleaseDates()));
        return movieReleaseDateDomainModel;
    }

    @Override
    public List<MovieReleaseDateDomainModel> mapListToDomainModelList(List<MovieReleaseDateDataModel> movieReleaseDateDataModels) {
        List<MovieReleaseDateDomainModel> movieReleaseDateDomainModels = new ArrayList<>();
        if(movieReleaseDateDataModels != null && movieReleaseDateDataModels.size()>0) {
            for (MovieReleaseDateDataModel movieReleaseDateDataModel : movieReleaseDateDataModels) {
                movieReleaseDateDomainModels.add(mapToDomainModel(movieReleaseDateDataModel));
            }
        }
        return movieReleaseDateDomainModels;
    }
}
