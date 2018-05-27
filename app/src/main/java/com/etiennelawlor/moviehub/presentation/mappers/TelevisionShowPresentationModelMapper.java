package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowPresentationModelMapper implements PresentationModelMapper<TelevisionShowDomainModel, TelevisionShowPresentationModel>, PresentationModelListMapper<TelevisionShowDomainModel, TelevisionShowPresentationModel> {

    // region Member Variables
    private GenrePresentationModelMapper genrePresentationModelMapper = new GenrePresentationModelMapper();
    private NetworkPresentationModelMapper networkPresentationModelMapper = new NetworkPresentationModelMapper();
    // endregion

    @Override
    public TelevisionShowPresentationModel mapToPresentationModel(TelevisionShowDomainModel televisionShowDomainModel) {
        TelevisionShowPresentationModel televisionShowPresentationModel = new TelevisionShowPresentationModel();
        televisionShowPresentationModel.setBackdropPath(televisionShowDomainModel.getBackdropPath());
        televisionShowPresentationModel.setEpisodeRunTime(televisionShowDomainModel.getEpisodeRunTime());
        televisionShowPresentationModel.setFirstAirDate(televisionShowDomainModel.getFirstAirDate());
        televisionShowPresentationModel.setGenres(genrePresentationModelMapper.mapListToPresentationModelList(televisionShowDomainModel.getGenres()));
        televisionShowPresentationModel.setHomepage(televisionShowDomainModel.getHomepage());
        televisionShowPresentationModel.setId(televisionShowDomainModel.getId());
        televisionShowPresentationModel.setInProduction(televisionShowDomainModel.isInProduction());
        televisionShowPresentationModel.setLanguages(televisionShowDomainModel.getLanguages());
        televisionShowPresentationModel.setLastAirDate(televisionShowDomainModel.getLastAirDate());
        televisionShowPresentationModel.setName(televisionShowDomainModel.getName());
        televisionShowPresentationModel.setNetworks(networkPresentationModelMapper.mapListToPresentationModelList(televisionShowDomainModel.getNetworks()));
        televisionShowPresentationModel.setNumberOfEpisodes(televisionShowDomainModel.getNumberOfEpisodes());
        televisionShowPresentationModel.setNumberOfSeasons(televisionShowDomainModel.getNumberOfSeasons());
        televisionShowPresentationModel.setOriginalLanguage(televisionShowDomainModel.getOriginalLanguage());
        televisionShowPresentationModel.setOriginalName(televisionShowDomainModel.getOriginalName());
        televisionShowPresentationModel.setOriginCountry(televisionShowDomainModel.getOriginCountry());
        televisionShowPresentationModel.setOverview(televisionShowDomainModel.getOverview());
        televisionShowPresentationModel.setPopularity(televisionShowDomainModel.getPopularity());
        televisionShowPresentationModel.setPosterPath(televisionShowDomainModel.getPosterPath());
        televisionShowPresentationModel.setStatus(televisionShowDomainModel.getStatus());
        televisionShowPresentationModel.setType(televisionShowDomainModel.getType());
        televisionShowPresentationModel.setVoteAverage(televisionShowDomainModel.getVoteAverage());
        televisionShowPresentationModel.setVoteCount(televisionShowDomainModel.getVoteCount());
        return televisionShowPresentationModel;
    }

    @Override
    public List<TelevisionShowPresentationModel> mapListToPresentationModelList(List<TelevisionShowDomainModel> televisionShowDomainModels) {
        List<TelevisionShowPresentationModel> televisionShowPresentationModels = new ArrayList<>();
        if(televisionShowDomainModels != null && televisionShowDomainModels.size()>0) {
            for (TelevisionShowDomainModel televisionShowDomainModel : televisionShowDomainModels) {
                televisionShowPresentationModels.add(mapToPresentationModel(televisionShowDomainModel));
            }
        }
        return televisionShowPresentationModels;
    }
}