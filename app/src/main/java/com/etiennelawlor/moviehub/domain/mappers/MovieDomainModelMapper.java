package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class MovieDomainModelMapper implements DomainModelMapper<MovieDataModel, MovieDomainModel>, DomainModelListMapper<MovieDataModel, MovieDomainModel> {

    // region Member Variables
    private GenreDomainModelMapper genreDomainModelMapper = new GenreDomainModelMapper();
    // endregion

    @Override
    public MovieDomainModel mapToDomainModel(MovieDataModel movieDataModel) {
        MovieDomainModel movieDomainModel = new MovieDomainModel();
        movieDomainModel.setAdult(movieDataModel.isAdult());
        movieDomainModel.setBackdropPath(movieDataModel.getBackdropPath());
        movieDomainModel.setBudget(movieDataModel.getBudget());
        movieDomainModel.setGenres(genreDomainModelMapper.mapListToDomainModelList(movieDataModel.getGenres()));
        movieDomainModel.setHomepage(movieDataModel.getHomepage());
        movieDomainModel.setId(movieDataModel.getId());
        movieDomainModel.setImdbId(movieDataModel.getImdbId());
        movieDomainModel.setOriginalLanguage(movieDataModel.getOriginalLanguage());
        movieDomainModel.setOriginalTitle(movieDataModel.getOriginalTitle());
        movieDomainModel.setOverview(movieDataModel.getOverview());
        movieDomainModel.setPopularity(movieDataModel.getPopularity());
        movieDomainModel.setPosterPath(movieDataModel.getPosterPath());
        movieDomainModel.setReleaseDate(movieDataModel.getReleaseDate());
        movieDomainModel.setRevenue(movieDataModel.getRevenue());
        movieDomainModel.setRuntime(movieDataModel.getRuntime());
        movieDomainModel.setStatus(movieDataModel.getStatus());
        movieDomainModel.setTagline(movieDataModel.getTagline());
        movieDomainModel.setTitle(movieDataModel.getTitle());
        movieDomainModel.setVideo(movieDataModel.isVideo());
        movieDomainModel.setVoteAverage(movieDataModel.getVoteAverage());
        movieDomainModel.setVoteCount(movieDataModel.getVoteCount());
        return movieDomainModel;
    }

    @Override
    public List<MovieDomainModel> mapListToDomainModelList(List<MovieDataModel> movieDataModels) {
        List<MovieDomainModel> movieDomainModels = new ArrayList<>();
        if(movieDataModels != null && movieDataModels.size()>0) {
            for (MovieDataModel movieDataModel : movieDataModels) {
                movieDomainModels.add(mapToDomainModel(movieDataModel));
            }
        }
        return movieDomainModels;
    }
}
