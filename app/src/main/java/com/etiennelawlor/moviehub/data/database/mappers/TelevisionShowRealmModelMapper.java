package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.database.models.IntegerRealmModel;
import com.etiennelawlor.moviehub.data.database.models.NetworkRealmModel;
import com.etiennelawlor.moviehub.data.database.models.StringRealmModel;
import com.etiennelawlor.moviehub.data.database.models.TelevisionShowRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.GenreDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.NetworkDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class TelevisionShowRealmModelMapper implements RealmModelMapper<TelevisionShowDataModel, TelevisionShowRealmModel> {

    private GenreRealmModelMapper genreRealmMapper = new GenreRealmModelMapper();
    private NetworkRealmModelMapper networkRealmMapper = new NetworkRealmModelMapper();
    private StringRealmModelMapper stringRealmMapper = new StringRealmModelMapper();
    private IntegerRealmModelMapper integerRealmMapper = new IntegerRealmModelMapper();

    @Override
    public TelevisionShowRealmModel mapToRealmModel(TelevisionShowDataModel televisionShowDataModel) {
        TelevisionShowRealmModel realmTelevisionShow = Realm.getDefaultInstance().createObject(TelevisionShowRealmModel.class);

        realmTelevisionShow.setBackdropPath(televisionShowDataModel.getBackdropPath());

        List<Integer> episodeRunTimes = televisionShowDataModel.getEpisodeRunTime();
        RealmList<IntegerRealmModel> realmIntegers = new RealmList<>();
        if(episodeRunTimes != null && episodeRunTimes.size()>0) {
            for (Integer episodeRunTime : episodeRunTimes) {
                realmIntegers.add(integerRealmMapper.mapToRealmModel(episodeRunTime));
            }
        }
        realmTelevisionShow.setEpisodeRunTime(realmIntegers);

        realmTelevisionShow.setFirstAirDate(televisionShowDataModel.getFirstAirDate());

        List<GenreDataModel> genreDataModels = televisionShowDataModel.getGenres();
        RealmList<GenreRealmModel> realmGenres = new RealmList<>();
        if(genreDataModels != null && genreDataModels.size()>0) {
            for (GenreDataModel genreDataModel : genreDataModels) {
                realmGenres.add(genreRealmMapper.mapToRealmModel(genreDataModel));
            }
        }
        realmTelevisionShow.setGenres(realmGenres);

        realmTelevisionShow.setHomepage(televisionShowDataModel.getHomepage());
        realmTelevisionShow.setId(televisionShowDataModel.getId());
        realmTelevisionShow.setInProduction(televisionShowDataModel.isInProduction());

        List<String> languages = televisionShowDataModel.getLanguages();
        RealmList<StringRealmModel> realmLanguages = new RealmList<>();
        if(languages != null && languages.size()>0) {
            for (String language : languages) {
                realmLanguages.add(stringRealmMapper.mapToRealmModel(language));
            }
        }
        realmTelevisionShow.setLanguages(realmLanguages);

        realmTelevisionShow.setLastAirDate(televisionShowDataModel.getLastAirDate());
        realmTelevisionShow.setName(televisionShowDataModel.getName());


        List<NetworkDataModel> networkDataModels = televisionShowDataModel.getNetworks();
        RealmList<NetworkRealmModel> networkRealmModels = new RealmList<>();
        if(networkDataModels != null && networkDataModels.size()>0) {
            for (NetworkDataModel networkDataModel : networkDataModels) {
                networkRealmModels.add(networkRealmMapper.mapToRealmModel(networkDataModel));
            }
        }
        realmTelevisionShow.setNetworks(networkRealmModels);

        realmTelevisionShow.setNumberOfEpisodes(televisionShowDataModel.getNumberOfEpisodes());
        realmTelevisionShow.setNumberOfSeasons(televisionShowDataModel.getNumberOfSeasons());

        List<String> originCountries = televisionShowDataModel.getOriginCountry();
        RealmList<StringRealmModel> realmOriginCountries = new RealmList<>();
        if(originCountries != null && originCountries.size()>0) {
            for (String originCountry : originCountries) {
                realmOriginCountries.add(stringRealmMapper.mapToRealmModel(originCountry));
            }
        }
        realmTelevisionShow.setOriginCountry(realmOriginCountries);

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

        RealmList<IntegerRealmModel> realmEpisodeRunTimes = televisionShowRealmModel.getEpisodeRunTime();
        List<Integer> episodeRunTimes = new ArrayList<>();
        for(IntegerRealmModel realmEpisodeRunTime : realmEpisodeRunTimes){
            episodeRunTimes.add(integerRealmMapper.mapFromRealmModel(realmEpisodeRunTime));
        }
        televisionShowDataModel.setEpisodeRunTime(episodeRunTimes);

        televisionShowDataModel.setFirstAirDate(televisionShowRealmModel.getFirstAirDate());

        RealmList<GenreRealmModel> realmGenres = televisionShowRealmModel.getGenres();
        List<GenreDataModel> genreDataModels = new ArrayList<>();
        for(GenreRealmModel realmGenre : realmGenres){
            genreDataModels.add(genreRealmMapper.mapFromRealmModel(realmGenre));
        }
        televisionShowDataModel.setGenres(genreDataModels);

        televisionShowDataModel.setHomepage(televisionShowRealmModel.getHomepage());
        televisionShowDataModel.setId(televisionShowRealmModel.getId());
        televisionShowDataModel.setInProduction(televisionShowRealmModel.isInProduction());

        RealmList<StringRealmModel> realmLanguages = televisionShowRealmModel.getLanguages();
        List<String> languages = new ArrayList<>();
        for(StringRealmModel realmLanguage : realmLanguages){
            languages.add(stringRealmMapper.mapFromRealmModel(realmLanguage));
        }
        televisionShowDataModel.setLanguages(languages);

        televisionShowDataModel.setLastAirDate(televisionShowRealmModel.getLastAirDate());
        televisionShowDataModel.setName(televisionShowRealmModel.getName());

        RealmList<NetworkRealmModel> networkRealmModels = televisionShowRealmModel.getNetworks();
        List<NetworkDataModel> networkDataModels = new ArrayList<>();
        for(NetworkRealmModel networkRealmModel : networkRealmModels){
            networkDataModels.add(networkRealmMapper.mapFromRealmModel(networkRealmModel));
        }
        televisionShowDataModel.setNetworks(networkDataModels);

        televisionShowDataModel.setNumberOfEpisodes(televisionShowRealmModel.getNumberOfEpisodes());
        televisionShowDataModel.setNumberOfSeasons(televisionShowRealmModel.getNumberOfSeasons());

        RealmList<StringRealmModel> realmOriginCountries = televisionShowRealmModel.getOriginCountry();
        List<String> originCountries = new ArrayList<>();
        for(StringRealmModel realmOriginCountry : realmOriginCountries){
            originCountries.add(stringRealmMapper.mapFromRealmModel(realmOriginCountry));
        }
        televisionShowDataModel.setOriginCountry(originCountries);

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
}
