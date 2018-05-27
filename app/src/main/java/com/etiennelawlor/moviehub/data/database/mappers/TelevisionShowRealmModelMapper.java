package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.TelevisionShowRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class TelevisionShowRealmModelMapper implements RealmModelMapper<TelevisionShowDataModel, TelevisionShowRealmModel>, RealmModelListMapper<TelevisionShowDataModel, TelevisionShowRealmModel> {

    private GenreRealmModelMapper genreRealmMapper = new GenreRealmModelMapper();
    private NetworkRealmModelMapper networkRealmMapper = new NetworkRealmModelMapper();
    private StringRealmModelMapper stringRealmMapper = new StringRealmModelMapper();
    private IntegerRealmModelMapper integerRealmMapper = new IntegerRealmModelMapper();

    @Override
    public TelevisionShowRealmModel mapToRealmModel(TelevisionShowDataModel televisionShowDataModel) {
        TelevisionShowRealmModel realmTelevisionShow = Realm.getDefaultInstance().createObject(TelevisionShowRealmModel.class);

        realmTelevisionShow.setBackdropPath(televisionShowDataModel.getBackdropPath());
        realmTelevisionShow.setEpisodeRunTime(integerRealmMapper.mapListToRealmModelList(televisionShowDataModel.getEpisodeRunTime()));
        realmTelevisionShow.setFirstAirDate(televisionShowDataModel.getFirstAirDate());
        realmTelevisionShow.setGenres(genreRealmMapper.mapListToRealmModelList(televisionShowDataModel.getGenres()));
        realmTelevisionShow.setHomepage(televisionShowDataModel.getHomepage());
        realmTelevisionShow.setId(televisionShowDataModel.getId());
        realmTelevisionShow.setInProduction(televisionShowDataModel.isInProduction());
        realmTelevisionShow.setLanguages(stringRealmMapper.mapListToRealmModelList(televisionShowDataModel.getLanguages()));
        realmTelevisionShow.setLastAirDate(televisionShowDataModel.getLastAirDate());
        realmTelevisionShow.setName(televisionShowDataModel.getName());
        realmTelevisionShow.setNetworks(networkRealmMapper.mapListToRealmModelList(televisionShowDataModel.getNetworks()));
        realmTelevisionShow.setNumberOfEpisodes(televisionShowDataModel.getNumberOfEpisodes());
        realmTelevisionShow.setNumberOfSeasons(televisionShowDataModel.getNumberOfSeasons());
        realmTelevisionShow.setOriginCountry(stringRealmMapper.mapListToRealmModelList(televisionShowDataModel.getOriginCountry()));
        realmTelevisionShow.setOriginalLanguage(televisionShowDataModel.getOriginalLanguage());
        realmTelevisionShow.setOriginalName(televisionShowDataModel.getOriginalName());
        realmTelevisionShow.setOverview(televisionShowDataModel.getOverview());
        realmTelevisionShow.setPopularity(televisionShowDataModel.getPopularity());
        realmTelevisionShow.setPosterPath(televisionShowDataModel.getPosterPath());
        realmTelevisionShow.setStatus(televisionShowDataModel.getStatus());
        realmTelevisionShow.setType(televisionShowDataModel.getType());
        realmTelevisionShow.setVoteAverage(televisionShowDataModel.getVoteAverage());
        realmTelevisionShow.setVoteCount(televisionShowDataModel.getVoteCount());

        return realmTelevisionShow;
    }

    @Override
    public TelevisionShowDataModel mapFromRealmModel(TelevisionShowRealmModel televisionShowRealmModel) {
        TelevisionShowDataModel televisionShowDataModel = new TelevisionShowDataModel();
        televisionShowDataModel.setBackdropPath(televisionShowRealmModel.getBackdropPath());
        televisionShowDataModel.setEpisodeRunTime(integerRealmMapper.mapListFromRealmModelList(televisionShowRealmModel.getEpisodeRunTime()));
        televisionShowDataModel.setFirstAirDate(televisionShowRealmModel.getFirstAirDate());
        televisionShowDataModel.setGenres(genreRealmMapper.mapListFromRealmModelList(televisionShowRealmModel.getGenres()));
        televisionShowDataModel.setHomepage(televisionShowRealmModel.getHomepage());
        televisionShowDataModel.setId(televisionShowRealmModel.getId());
        televisionShowDataModel.setInProduction(televisionShowRealmModel.isInProduction());
        televisionShowDataModel.setLanguages(stringRealmMapper.mapListFromRealmModelList(televisionShowRealmModel.getLanguages()));
        televisionShowDataModel.setLastAirDate(televisionShowRealmModel.getLastAirDate());
        televisionShowDataModel.setName(televisionShowRealmModel.getName());
        televisionShowDataModel.setNetworks(networkRealmMapper.mapListFromRealmModelList(televisionShowRealmModel.getNetworks()));
        televisionShowDataModel.setNumberOfEpisodes(televisionShowRealmModel.getNumberOfEpisodes());
        televisionShowDataModel.setNumberOfSeasons(televisionShowRealmModel.getNumberOfSeasons());
        televisionShowDataModel.setOriginCountry(stringRealmMapper.mapListFromRealmModelList(televisionShowRealmModel.getOriginCountry()));
        televisionShowDataModel.setOriginalLanguage(televisionShowRealmModel.getOriginalLanguage());
        televisionShowDataModel.setOriginalName(televisionShowRealmModel.getOriginalName());
        televisionShowDataModel.setOverview(televisionShowRealmModel.getOverview());
        televisionShowDataModel.setPopularity(televisionShowRealmModel.getPopularity());
        televisionShowDataModel.setPosterPath(televisionShowRealmModel.getPosterPath());
        televisionShowDataModel.setStatus(televisionShowRealmModel.getStatus());
        televisionShowDataModel.setType(televisionShowRealmModel.getType());
        televisionShowDataModel.setVoteAverage(televisionShowRealmModel.getVoteAverage());
        televisionShowDataModel.setVoteCount(televisionShowRealmModel.getVoteCount());

        return televisionShowDataModel;
    }

    @Override
    public RealmList<TelevisionShowRealmModel> mapListToRealmModelList(List<TelevisionShowDataModel> televisionShowDataModels) {
        RealmList<TelevisionShowRealmModel> televisionShowRealmModels = new RealmList<>();
        for(TelevisionShowDataModel televisionShowDataModel : televisionShowDataModels){
            televisionShowRealmModels.add(mapToRealmModel(televisionShowDataModel));
        }
        return televisionShowRealmModels;
    }

    @Override
    public List<TelevisionShowDataModel> mapListFromRealmModelList(RealmList<TelevisionShowRealmModel> televisionShowRealmModels) {
        List<TelevisionShowDataModel> televisionShowDataModels = new ArrayList<>();
        for(TelevisionShowRealmModel televisionShowRealmModel : televisionShowRealmModels){
            televisionShowDataModels.add(mapFromRealmModel(televisionShowRealmModel));
        }
        return televisionShowDataModels;
    }
}
