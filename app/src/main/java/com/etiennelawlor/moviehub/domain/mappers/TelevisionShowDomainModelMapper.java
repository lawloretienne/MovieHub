package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowDomainModelMapper implements DomainModelMapper<TelevisionShowDataModel, TelevisionShowDomainModel>, DomainModelListMapper<TelevisionShowDataModel, TelevisionShowDomainModel> {

    // region Member Variables
    private GenreDomainModelMapper genreDomainModelMapper = new GenreDomainModelMapper();
    private NetworkDomainModelMapper networkDomainModelMapper = new NetworkDomainModelMapper();
    // endregion

    @Override
    public TelevisionShowDomainModel mapToDomainModel(TelevisionShowDataModel televisionShowDataModel) {
        TelevisionShowDomainModel televisionShowDomainModel = new TelevisionShowDomainModel();
        televisionShowDomainModel.setBackdropPath(televisionShowDataModel.getBackdropPath());
        televisionShowDomainModel.setEpisodeRunTime(televisionShowDataModel.getEpisodeRunTime());
        televisionShowDomainModel.setFirstAirDate(televisionShowDataModel.getFirstAirDate());
        televisionShowDomainModel.setGenres(genreDomainModelMapper.mapListToDomainModelList(televisionShowDataModel.getGenres()));
        televisionShowDomainModel.setHomepage(televisionShowDataModel.getHomepage());
        televisionShowDomainModel.setId(televisionShowDataModel.getId());
        televisionShowDomainModel.setInProduction(televisionShowDataModel.isInProduction());
        televisionShowDomainModel.setLanguages(televisionShowDataModel.getLanguages());
        televisionShowDomainModel.setLastAirDate(televisionShowDataModel.getLastAirDate());
        televisionShowDomainModel.setName(televisionShowDataModel.getName());
        televisionShowDomainModel.setNetworks(networkDomainModelMapper.mapListToDomainModelList(televisionShowDataModel.getNetworks()));
        televisionShowDomainModel.setNumberOfEpisodes(televisionShowDataModel.getNumberOfEpisodes());
        televisionShowDomainModel.setNumberOfSeasons(televisionShowDataModel.getNumberOfSeasons());
        televisionShowDomainModel.setOriginalLanguage(televisionShowDataModel.getOriginalLanguage());
        televisionShowDomainModel.setOriginalName(televisionShowDataModel.getOriginalName());
        televisionShowDomainModel.setOriginCountry(televisionShowDataModel.getOriginCountry());
        televisionShowDomainModel.setOverview(televisionShowDataModel.getOverview());
        televisionShowDomainModel.setPopularity(televisionShowDataModel.getPopularity());
        televisionShowDomainModel.setPosterPath(televisionShowDataModel.getPosterPath());
        televisionShowDomainModel.setStatus(televisionShowDataModel.getStatus());
        televisionShowDomainModel.setType(televisionShowDataModel.getType());
        televisionShowDomainModel.setVoteAverage(televisionShowDataModel.getVoteAverage());
        televisionShowDomainModel.setVoteCount(televisionShowDataModel.getVoteCount());
        return televisionShowDomainModel;
    }

    @Override
    public List<TelevisionShowDomainModel> mapListToDomainModelList(List<TelevisionShowDataModel> televisionShowDataModels) {
        List<TelevisionShowDomainModel> televisionShowDomainModels = new ArrayList<>();
        if(televisionShowDataModels != null && televisionShowDataModels.size()>0) {
            for (TelevisionShowDataModel televisionShowDataModel : televisionShowDataModels) {
                televisionShowDomainModels.add(mapToDomainModel(televisionShowDataModel));
            }
        }
        return televisionShowDomainModels;
    }
}