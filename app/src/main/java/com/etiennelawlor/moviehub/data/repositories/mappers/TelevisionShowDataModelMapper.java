package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShowResponse;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowDataModelMapper implements DataModelMapper<TelevisionShowResponse, TelevisionShowDataModel>, DataModelListMapper<TelevisionShowResponse, TelevisionShowDataModel> {

    // region Member Variables
    private GenreDataModelMapper genreDataModelMapper = new GenreDataModelMapper();
    private NetworkDataModelMapper networkDataModelMapper = new NetworkDataModelMapper();
    // endregion

    @Override
    public TelevisionShowDataModel mapToDataModel(TelevisionShowResponse televisionShowResponse) {
        TelevisionShowDataModel televisionShowDataModel = new TelevisionShowDataModel();
        televisionShowDataModel.setBackdropPath(televisionShowResponse.getBackdropPath());
        televisionShowDataModel.setEpisodeRunTime(televisionShowResponse.getEpisodeRunTime());
        televisionShowDataModel.setFirstAirDate(televisionShowResponse.getFirstAirDate());
        televisionShowDataModel.setGenres(genreDataModelMapper.mapListToDataModelList(televisionShowResponse.getGenres()));
        televisionShowDataModel.setHomepage(televisionShowResponse.getHomepage());
        televisionShowDataModel.setId(televisionShowResponse.getId());
        televisionShowDataModel.setInProduction(televisionShowResponse.isInProduction());
        televisionShowDataModel.setLanguages(televisionShowResponse.getLanguages());
        televisionShowDataModel.setLastAirDate(televisionShowResponse.getLastAirDate());
        televisionShowDataModel.setName(televisionShowResponse.getName());
        televisionShowDataModel.setNetworks(networkDataModelMapper.mapListToDataModelList(televisionShowResponse.getNetworks()));
        televisionShowDataModel.setNumberOfEpisodes(televisionShowResponse.getNumberOfEpisodes());
        televisionShowDataModel.setNumberOfSeasons(televisionShowResponse.getNumberOfSeasons());
        televisionShowDataModel.setOriginalLanguage(televisionShowResponse.getOriginalLanguage());
        televisionShowDataModel.setOriginalName(televisionShowResponse.getOriginalName());
        televisionShowDataModel.setOriginCountry(televisionShowResponse.getOriginCountry());
        televisionShowDataModel.setOverview(televisionShowResponse.getOverview());
        televisionShowDataModel.setPopularity(televisionShowResponse.getPopularity());
        televisionShowDataModel.setPosterPath(televisionShowResponse.getPosterPath());
        televisionShowDataModel.setStatus(televisionShowResponse.getStatus());
        televisionShowDataModel.setType(televisionShowResponse.getType());
        televisionShowDataModel.setVoteAverage(televisionShowResponse.getVoteAverage());
        televisionShowDataModel.setVoteCount(televisionShowResponse.getVoteCount());
        return televisionShowDataModel;
    }

    @Override
    public List<TelevisionShowDataModel> mapListToDataModelList(List<TelevisionShowResponse> televisionShowResponses) {
        List<TelevisionShowDataModel> televisionShowDataModels = new ArrayList<>();
        if(televisionShowResponses != null && televisionShowResponses.size()>0) {
            for (TelevisionShowResponse televisionShowResponse : televisionShowResponses) {
                televisionShowDataModels.add(mapToDataModel(televisionShowResponse));
            }
        }
        return televisionShowDataModels;
    }
}