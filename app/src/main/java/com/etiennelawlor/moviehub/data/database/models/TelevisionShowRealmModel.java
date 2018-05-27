package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShowRealmModel extends RealmObject {

    // region Fields
    public String backdropPath;
    public RealmList<IntegerRealmModel> episodeRunTime = null;
    public String firstAirDate;
    public RealmList<GenreRealmModel> genres = null;
    public String homepage;
    public int id;
    public boolean inProduction;
    public RealmList<StringRealmModel> languages = null;
    public String lastAirDate;
    public String name;
    public RealmList<NetworkRealmModel> networks = null;
    public int numberOfEpisodes;
    public int numberOfSeasons;
    public RealmList<StringRealmModel> originCountry = null;
    public String originalLanguage;
    public String originalName;
    public String overview;
    public float popularity;
    public String posterPath;
    public String status;
    public String type;
    public float voteAverage;
    public int voteCount;
    // endregion

    // region Getters

    public String getBackdropPath() {
        return backdropPath;
    }

    public RealmList<IntegerRealmModel> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public RealmList<GenreRealmModel> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public RealmList<StringRealmModel> getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public String getName() {
        return name;
    }

    public RealmList<NetworkRealmModel> getNetworks() {
        return networks;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public RealmList<StringRealmModel> getOriginCountry() {
        return originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    // endregion

    // region Setters

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setEpisodeRunTime(RealmList<IntegerRealmModel> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public void setGenres(RealmList<GenreRealmModel> genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public void setLanguages(RealmList<StringRealmModel> languages) {
        this.languages = languages;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetworks(RealmList<NetworkRealmModel> networks) {
        this.networks = networks;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void setOriginCountry(RealmList<StringRealmModel> originCountry) {
        this.originCountry = originCountry;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    // endregion
}
