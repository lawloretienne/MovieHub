package com.etiennelawlor.moviehub.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.Palette;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class TelevisionShow implements Parcelable {

    // region Fields
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("episode_run_time")
    public List<Integer> episodeRunTime = null;
    @SerializedName("first_air_date")
    public String firstAirDate;
    @SerializedName("genres")
    public List<Genre> genres = null;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public Integer id;
    @SerializedName("in_production")
    public Boolean inProduction;
    @SerializedName("languages")
    public List<String> languages = null;
    @SerializedName("last_air_date")
    public String lastAirDate;
    @SerializedName("name")
    public String name;
    @SerializedName("networks")
    public List<Network> networks = null;
    @SerializedName("number_of_episodes")
    public Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    public Integer numberOfSeasons;
    @SerializedName("origin_country")
    public List<String> originCountry = null;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_name")
    public String originalName;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public Float popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("status")
    public String status;
    @SerializedName("type")
    public String type;
    @SerializedName("vote_average")
    public Float voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;

    private Palette posterPalette;
    // endregion

    // region Constructors
    public TelevisionShow() {
    }

    protected TelevisionShow(Parcel in) {
        this.backdropPath = in.readString();
        this.episodeRunTime = new ArrayList<Integer>();
        in.readList(this.episodeRunTime, Integer.class.getClassLoader());
        this.firstAirDate = in.readString();
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.homepage = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inProduction = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.languages = in.createStringArrayList();
        this.lastAirDate = in.readString();
        this.name = in.readString();
        this.networks = new ArrayList<Network>();
        in.readList(this.networks, Network.class.getClassLoader());
        this.numberOfEpisodes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numberOfSeasons = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originCountry = in.createStringArrayList();
        this.originalLanguage = in.readString();
        this.originalName = in.readString();
        this.overview = in.readString();
        this.popularity = (Float) in.readValue(Float.class.getClassLoader());
        this.posterPath = in.readString();
        this.status = in.readString();
        this.type = in.readString();
        this.voteAverage = (Float) in.readValue(Integer.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.posterPalette = in.readParcelable(Palette.class.getClassLoader());
    }
    // endregion

    // region Getters

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getInProduction() {
        return inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public String getName() {
        return name;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public List<String> getOriginCountry() {
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

    public Float getPopularity() {
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

    public Float getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Palette getPosterPalette() {
        return posterPalette;
    }

    // endregion

    // region Setters

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void setOriginCountry(List<String> originCountry) {
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

    public void setPopularity(Float popularity) {
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

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void setPosterPalette(Palette posterPalette) {
        this.posterPalette = posterPalette;
    }

    // endregion

    // region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeList(this.episodeRunTime);
        dest.writeString(this.firstAirDate);
        dest.writeTypedList(this.genres);
        dest.writeString(this.homepage);
        dest.writeValue(this.id);
        dest.writeValue(this.inProduction);
        dest.writeStringList(this.languages);
        dest.writeString(this.lastAirDate);
        dest.writeString(this.name);
        dest.writeList(this.networks);
        dest.writeValue(this.numberOfEpisodes);
        dest.writeValue(this.numberOfSeasons);
        dest.writeStringList(this.originCountry);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalName);
        dest.writeString(this.overview);
        dest.writeValue(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
    }
    // endregion

    public static final Parcelable.Creator<TelevisionShow> CREATOR = new Parcelable.Creator<TelevisionShow>() {
        @Override
        public TelevisionShow createFromParcel(Parcel source) {
            return new TelevisionShow(source);
        }

        @Override
        public TelevisionShow[] newArray(int size) {
            return new TelevisionShow[size];
        }
    };
}
