package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MoviePresentationModelMapper implements PresentationModelMapper<MovieDomainModel, MoviePresentationModel>, PresentationModelListMapper<MovieDomainModel, MoviePresentationModel> {

    // region Member Variables
    private GenrePresentationModelMapper genrePresentationModelMapper = new GenrePresentationModelMapper();
    // endregion

    @Override
    public MoviePresentationModel mapToPresentationModel(MovieDomainModel movieDomainModel) {
        MoviePresentationModel moviePresentationModel = new MoviePresentationModel();
        moviePresentationModel.setAdult(movieDomainModel.isAdult());
        moviePresentationModel.setBackdropPath(movieDomainModel.getBackdropPath());
        moviePresentationModel.setBudget(movieDomainModel.getBudget());
        moviePresentationModel.setGenres(genrePresentationModelMapper.mapListToPresentationModelList(movieDomainModel.getGenres()));
        moviePresentationModel.setHomepage(movieDomainModel.getHomepage());
        moviePresentationModel.setId(movieDomainModel.getId());
        moviePresentationModel.setImdbId(movieDomainModel.getImdbId());
        moviePresentationModel.setOriginalLanguage(movieDomainModel.getOriginalLanguage());
        moviePresentationModel.setOriginalTitle(movieDomainModel.getOriginalTitle());
        moviePresentationModel.setOverview(movieDomainModel.getOverview());
        moviePresentationModel.setPopularity(movieDomainModel.getPopularity());
        moviePresentationModel.setPosterPath(movieDomainModel.getPosterPath());
        moviePresentationModel.setReleaseDate(movieDomainModel.getReleaseDate());
        moviePresentationModel.setRevenue(movieDomainModel.getRevenue());
        moviePresentationModel.setRuntime(movieDomainModel.getRuntime());
        moviePresentationModel.setStatus(movieDomainModel.getStatus());
        moviePresentationModel.setTagline(movieDomainModel.getTagline());
        moviePresentationModel.setTitle(movieDomainModel.getTitle());
        moviePresentationModel.setVideo(movieDomainModel.isVideo());
        moviePresentationModel.setVoteAverage(movieDomainModel.getVoteAverage());
        moviePresentationModel.setVoteCount(movieDomainModel.getVoteCount());
        return moviePresentationModel;
    }

    @Override
    public List<MoviePresentationModel> mapListToPresentationModelList(List<MovieDomainModel> movieDomainModels) {
        List<MoviePresentationModel> moviePresentationModels = new ArrayList<>();
        if(movieDomainModels != null && movieDomainModels.size()>0) {
            for (MovieDomainModel movieDomainModel : movieDomainModels) {
                moviePresentationModels.add(mapToPresentationModel(movieDomainModel));
            }
        }
        return moviePresentationModels;
    }
}
