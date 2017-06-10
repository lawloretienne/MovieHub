package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmGenre;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmInteger;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmNetwork;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmString;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmTelevisionShow;
import com.etiennelawlor.moviehub.data.remote.response.Genre;
import com.etiennelawlor.moviehub.data.remote.response.Network;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class TelevisionShowRealmMapper implements RealmMapper<TelevisionShow, RealmTelevisionShow> {

    private GenreRealmMapper genreRealmMapper = new GenreRealmMapper();
    private NetworkRealmMapper networkRealmMapper = new NetworkRealmMapper();
    private StringRealmMapper stringRealmMapper = new StringRealmMapper();
    private IntegerRealmMapper integerRealmMapper = new IntegerRealmMapper();

    @Override
    public RealmTelevisionShow mapToRealmObject(TelevisionShow televisionShow) {
        RealmTelevisionShow realmTelevisionShow = Realm.getDefaultInstance().createObject(RealmTelevisionShow.class);

        realmTelevisionShow.setBackdropPath(televisionShow.getBackdropPath());

        List<Integer> episodeRunTimes = televisionShow.getEpisodeRunTime();
        RealmList<RealmInteger> realmIntegers = new RealmList<>();
        if(episodeRunTimes != null && episodeRunTimes.size()>0) {
            for (Integer episodeRunTime : episodeRunTimes) {
                realmIntegers.add(integerRealmMapper.mapToRealmObject(episodeRunTime));
            }
        }
        realmTelevisionShow.setEpisodeRunTime(realmIntegers);

        realmTelevisionShow.setFirstAirDate(televisionShow.getFirstAirDate());

        List<Genre> genres = televisionShow.getGenres();
        RealmList<RealmGenre> realmGenres = new RealmList<>();
        if(genres != null && genres.size()>0) {
            for (Genre genre : genres) {
                realmGenres.add(genreRealmMapper.mapToRealmObject(genre));
            }
        }
        realmTelevisionShow.setGenres(realmGenres);

        realmTelevisionShow.setHomepage(televisionShow.getHomepage());
        realmTelevisionShow.setId(televisionShow.getId());
        realmTelevisionShow.setInProduction(televisionShow.isInProduction());

        List<String> languages = televisionShow.getLanguages();
        RealmList<RealmString> realmLanguages = new RealmList<>();
        if(languages != null && languages.size()>0) {
            for (String language : languages) {
                realmLanguages.add(stringRealmMapper.mapToRealmObject(language));
            }
        }
        realmTelevisionShow.setLanguages(realmLanguages);

        realmTelevisionShow.setLastAirDate(televisionShow.getLastAirDate());
        realmTelevisionShow.setName(televisionShow.getName());


        List<Network> networks = televisionShow.getNetworks();
        RealmList<RealmNetwork> realmNetworks = new RealmList<>();
        if(networks != null && networks.size()>0) {
            for (Network network : networks) {
                realmNetworks.add(networkRealmMapper.mapToRealmObject(network));
            }
        }
        realmTelevisionShow.setNetworks(realmNetworks);

        realmTelevisionShow.setNumberOfEpisodes(televisionShow.getNumberOfEpisodes());
        realmTelevisionShow.setNumberOfSeasons(televisionShow.getNumberOfSeasons());

        List<String> originCountries = televisionShow.getOriginCountry();
        RealmList<RealmString> realmOriginCountries = new RealmList<>();
        if(originCountries != null && originCountries.size()>0) {
            for (String originCountry : originCountries) {
                realmOriginCountries.add(stringRealmMapper.mapToRealmObject(originCountry));
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
    public TelevisionShow mapFromRealmObject(RealmTelevisionShow realmTelevisionShow) {
        TelevisionShow televisionShow = new TelevisionShow();

        televisionShow.setBackdropPath(realmTelevisionShow.getBackdropPath());

        RealmList<RealmInteger> realmEpisodeRunTimes = realmTelevisionShow.getEpisodeRunTime();
        List<Integer> episodeRunTimes = new ArrayList<>();
        for(RealmInteger realmEpisodeRunTime : realmEpisodeRunTimes){
            episodeRunTimes.add(integerRealmMapper.mapFromRealmObject(realmEpisodeRunTime));
        }
        televisionShow.setEpisodeRunTime(episodeRunTimes);

        televisionShow.setFirstAirDate(realmTelevisionShow.getFirstAirDate());

        RealmList<RealmGenre> realmGenres = realmTelevisionShow.getGenres();
        List<Genre> genres = new ArrayList<>();
        for(RealmGenre realmGenre : realmGenres){
            genres.add(genreRealmMapper.mapFromRealmObject(realmGenre));
        }
        televisionShow.setGenres(genres);

        televisionShow.setHomepage(realmTelevisionShow.getHomepage());
        televisionShow.setId(realmTelevisionShow.getId());
        televisionShow.setInProduction(realmTelevisionShow.isInProduction());

        RealmList<RealmString> realmLanguages = realmTelevisionShow.getLanguages();
        List<String> languages = new ArrayList<>();
        for(RealmString realmLanguage : realmLanguages){
            languages.add(stringRealmMapper.mapFromRealmObject(realmLanguage));
        }
        televisionShow.setLanguages(languages);

        televisionShow.setLastAirDate(realmTelevisionShow.getLastAirDate());
        televisionShow.setName(realmTelevisionShow.getName());

        RealmList<RealmNetwork> realmNetworks = realmTelevisionShow.getNetworks();
        List<Network> networks = new ArrayList<>();
        for(RealmNetwork realmNetwork : realmNetworks){
            networks.add(networkRealmMapper.mapFromRealmObject(realmNetwork));
        }
        televisionShow.setNetworks(networks);

        televisionShow.setNumberOfEpisodes(realmTelevisionShow.getNumberOfEpisodes());
        televisionShow.setNumberOfSeasons(realmTelevisionShow.getNumberOfSeasons());

        RealmList<RealmString> realmOriginCountries = realmTelevisionShow.getOriginCountry();
        List<String> originCountries = new ArrayList<>();
        for(RealmString realmOriginCountry : realmOriginCountries){
            originCountries.add(stringRealmMapper.mapFromRealmObject(realmOriginCountry));
        }
        televisionShow.setOriginCountry(originCountries);

        televisionShow.setOriginalLanguage(realmTelevisionShow.getOriginalLanguage());
        televisionShow.setOriginalName(realmTelevisionShow.getOriginalName());
        televisionShow.setOverview(realmTelevisionShow.getOverview());
        televisionShow.setPopularity(realmTelevisionShow.getPopularity());
        televisionShow.setPosterPath(realmTelevisionShow.getPosterPath());
        televisionShow.setStatus(realmTelevisionShow.getStatus());
        televisionShow.setType(realmTelevisionShow.getType());
        televisionShow.setVoteAverage(realmTelevisionShow.getVoteAverage());
        televisionShow.setVoteCount(realmTelevisionShow.getVoteCount());

        return televisionShow;
    }
}
