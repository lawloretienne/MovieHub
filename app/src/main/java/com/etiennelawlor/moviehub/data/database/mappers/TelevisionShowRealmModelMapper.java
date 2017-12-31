package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.database.models.IntegerRealmModel;
import com.etiennelawlor.moviehub.data.database.models.NetworkRealmModel;
import com.etiennelawlor.moviehub.data.database.models.StringRealmModel;
import com.etiennelawlor.moviehub.data.database.models.TelevisionShowRealmModel;
import com.etiennelawlor.moviehub.data.network.response.Genre;
import com.etiennelawlor.moviehub.data.network.response.Network;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class TelevisionShowRealmModelMapper implements RealmModelMapper<TelevisionShow, TelevisionShowRealmModel> {

    private GenreRealmModelMapper genreRealmMapper = new GenreRealmModelMapper();
    private NetworkRealmModelMapper networkRealmMapper = new NetworkRealmModelMapper();
    private StringRealmModelMapper stringRealmMapper = new StringRealmModelMapper();
    private IntegerRealmModelMapper integerRealmMapper = new IntegerRealmModelMapper();

    @Override
    public TelevisionShowRealmModel mapToRealmModel(TelevisionShow televisionShow) {
        TelevisionShowRealmModel realmTelevisionShow = Realm.getDefaultInstance().createObject(TelevisionShowRealmModel.class);

        realmTelevisionShow.setBackdropPath(televisionShow.getBackdropPath());

        List<Integer> episodeRunTimes = televisionShow.getEpisodeRunTime();
        RealmList<IntegerRealmModel> realmIntegers = new RealmList<>();
        if(episodeRunTimes != null && episodeRunTimes.size()>0) {
            for (Integer episodeRunTime : episodeRunTimes) {
                realmIntegers.add(integerRealmMapper.mapToRealmModel(episodeRunTime));
            }
        }
        realmTelevisionShow.setEpisodeRunTime(realmIntegers);

        realmTelevisionShow.setFirstAirDate(televisionShow.getFirstAirDate());

        List<Genre> genres = televisionShow.getGenres();
        RealmList<GenreRealmModel> realmGenres = new RealmList<>();
        if(genres != null && genres.size()>0) {
            for (Genre genre : genres) {
                realmGenres.add(genreRealmMapper.mapToRealmModel(genre));
            }
        }
        realmTelevisionShow.setGenres(realmGenres);

        realmTelevisionShow.setHomepage(televisionShow.getHomepage());
        realmTelevisionShow.setId(televisionShow.getId());
        realmTelevisionShow.setInProduction(televisionShow.isInProduction());

        List<String> languages = televisionShow.getLanguages();
        RealmList<StringRealmModel> realmLanguages = new RealmList<>();
        if(languages != null && languages.size()>0) {
            for (String language : languages) {
                realmLanguages.add(stringRealmMapper.mapToRealmModel(language));
            }
        }
        realmTelevisionShow.setLanguages(realmLanguages);

        realmTelevisionShow.setLastAirDate(televisionShow.getLastAirDate());
        realmTelevisionShow.setName(televisionShow.getName());


        List<Network> networks = televisionShow.getNetworks();
        RealmList<NetworkRealmModel> realmNetworks = new RealmList<>();
        if(networks != null && networks.size()>0) {
            for (Network network : networks) {
                realmNetworks.add(networkRealmMapper.mapToRealmModel(network));
            }
        }
        realmTelevisionShow.setNetworks(realmNetworks);

        realmTelevisionShow.setNumberOfEpisodes(televisionShow.getNumberOfEpisodes());
        realmTelevisionShow.setNumberOfSeasons(televisionShow.getNumberOfSeasons());

        List<String> originCountries = televisionShow.getOriginCountry();
        RealmList<StringRealmModel> realmOriginCountries = new RealmList<>();
        if(originCountries != null && originCountries.size()>0) {
            for (String originCountry : originCountries) {
                realmOriginCountries.add(stringRealmMapper.mapToRealmModel(originCountry));
            }
        }
        realmTelevisionShow.setOriginCountry(realmOriginCountries);

        realmTelevisionShow.setOriginalLanguage(televisionShow.getOriginalLanguage());
        realmTelevisionShow.setOriginalName(televisionShow.getOriginalName());
        realmTelevisionShow.setOverview(televisionShow.getOverview());
        realmTelevisionShow.setPopularity(televisionShow.getPopularity());
        realmTelevisionShow.setPosterPath(televisionShow.getPosterPath());
        realmTelevisionShow.setStatus(televisionShow.getStatus());
        realmTelevisionShow.setType(televisionShow.getType());
        realmTelevisionShow.setVoteAverage(televisionShow.getVoteAverage());
        realmTelevisionShow.setVoteCount(televisionShow.getVoteCount());

        return realmTelevisionShow;
    }

    @Override
    public TelevisionShow mapFromRealmModel(TelevisionShowRealmModel televisionShowRealmModel) {
        TelevisionShow televisionShow = new TelevisionShow();

        televisionShow.setBackdropPath(televisionShowRealmModel.getBackdropPath());

        RealmList<IntegerRealmModel> realmEpisodeRunTimes = televisionShowRealmModel.getEpisodeRunTime();
        List<Integer> episodeRunTimes = new ArrayList<>();
        for(IntegerRealmModel realmEpisodeRunTime : realmEpisodeRunTimes){
            episodeRunTimes.add(integerRealmMapper.mapFromRealmModel(realmEpisodeRunTime));
        }
        televisionShow.setEpisodeRunTime(episodeRunTimes);

        televisionShow.setFirstAirDate(televisionShowRealmModel.getFirstAirDate());

        RealmList<GenreRealmModel> realmGenres = televisionShowRealmModel.getGenres();
        List<Genre> genres = new ArrayList<>();
        for(GenreRealmModel realmGenre : realmGenres){
            genres.add(genreRealmMapper.mapFromRealmModel(realmGenre));
        }
        televisionShow.setGenres(genres);

        televisionShow.setHomepage(televisionShowRealmModel.getHomepage());
        televisionShow.setId(televisionShowRealmModel.getId());
        televisionShow.setInProduction(televisionShowRealmModel.isInProduction());

        RealmList<StringRealmModel> realmLanguages = televisionShowRealmModel.getLanguages();
        List<String> languages = new ArrayList<>();
        for(StringRealmModel realmLanguage : realmLanguages){
            languages.add(stringRealmMapper.mapFromRealmModel(realmLanguage));
        }
        televisionShow.setLanguages(languages);

        televisionShow.setLastAirDate(televisionShowRealmModel.getLastAirDate());
        televisionShow.setName(televisionShowRealmModel.getName());

        RealmList<NetworkRealmModel> realmNetworks = televisionShowRealmModel.getNetworks();
        List<Network> networks = new ArrayList<>();
        for(NetworkRealmModel realmNetwork : realmNetworks){
            networks.add(networkRealmMapper.mapFromRealmModel(realmNetwork));
        }
        televisionShow.setNetworks(networks);

        televisionShow.setNumberOfEpisodes(televisionShowRealmModel.getNumberOfEpisodes());
        televisionShow.setNumberOfSeasons(televisionShowRealmModel.getNumberOfSeasons());

        RealmList<StringRealmModel> realmOriginCountries = televisionShowRealmModel.getOriginCountry();
        List<String> originCountries = new ArrayList<>();
        for(StringRealmModel realmOriginCountry : realmOriginCountries){
            originCountries.add(stringRealmMapper.mapFromRealmModel(realmOriginCountry));
        }
        televisionShow.setOriginCountry(originCountries);

        televisionShow.setOriginalLanguage(televisionShowRealmModel.getOriginalLanguage());
        televisionShow.setOriginalName(televisionShowRealmModel.getOriginalName());
        televisionShow.setOverview(televisionShowRealmModel.getOverview());
        televisionShow.setPopularity(televisionShowRealmModel.getPopularity());
        televisionShow.setPosterPath(televisionShowRealmModel.getPosterPath());
        televisionShow.setStatus(televisionShowRealmModel.getStatus());
        televisionShow.setType(televisionShowRealmModel.getType());
        televisionShow.setVoteAverage(televisionShowRealmModel.getVoteAverage());
        televisionShow.setVoteCount(televisionShowRealmModel.getVoteCount());

        return televisionShow;
    }
}
