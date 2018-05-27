package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MovieReleaseDateDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MovieReleaseDatePresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class MovieReleaseDatePresentationModelMapper implements PresentationModelMapper<MovieReleaseDateDomainModel, MovieReleaseDatePresentationModel>, PresentationModelListMapper<MovieReleaseDateDomainModel, MovieReleaseDatePresentationModel> {

    // region Member Variables
    private ReleaseDatePresentationModelMapper releaseDatePresentationModelMapper = new ReleaseDatePresentationModelMapper();
    // endregion

    @Override
    public MovieReleaseDatePresentationModel mapToPresentationModel(MovieReleaseDateDomainModel movieReleaseDateDomainModel) {
        MovieReleaseDatePresentationModel movieReleaseDatePresentationModel = new MovieReleaseDatePresentationModel();
        movieReleaseDatePresentationModel.setIso31661(movieReleaseDateDomainModel.getIso31661());
        movieReleaseDatePresentationModel.setReleaseDates(releaseDatePresentationModelMapper.mapListToPresentationModelList(movieReleaseDateDomainModel.getReleaseDates()));
        return movieReleaseDatePresentationModel;
    }

    @Override
    public List<MovieReleaseDatePresentationModel> mapListToPresentationModelList(List<MovieReleaseDateDomainModel> movieReleaseDateDomainModels) {
        List<MovieReleaseDatePresentationModel> movieReleaseDatePresentationModels = new ArrayList<>();
        if(movieReleaseDateDomainModels != null && movieReleaseDateDomainModels.size()>0) {
            for (MovieReleaseDateDomainModel movieReleaseDateDomainModel : movieReleaseDateDomainModels) {
                movieReleaseDatePresentationModels.add(mapToPresentationModel(movieReleaseDateDomainModel));
            }
        }
        return movieReleaseDatePresentationModels;
    }
}
