package com.etiennelawlor.moviehub.domain.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;

import com.etiennelawlor.moviehub.util.DateUtility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowDomainModel implements Parcelable {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    public static final String SECURE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE = "w500";
    // endregion

    // region Fields
    public String backdropPath;
    public List<Integer> episodeRunTime = null;
    public String firstAirDate;
    public List<GenreDomainModel> genres = null;
    public String homepage;
    public int id;
    public boolean inProduction;
    public List<String> languages = null;
    public String lastAirDate;
    public String name;
    public List<NetworkDomainModel> networks = null;
    public int numberOfEpisodes;
    public int numberOfSeasons;
    public List<String> originCountry = null;
    public String originalLanguage;
    public String originalName;
    public String overview;
    public float popularity;
    public String posterPath;
    public String status;
    public String type;
    public float voteAverage;
    public int voteCount;

    private Palette posterPalette;
    // endregion

    // region Constructors
    public TelevisionShowDomainModel() {
    }

    protected TelevisionShowDomainModel(Parcel in) {
        this.backdropPath = in.readString();
        this.episodeRunTime = new ArrayList<Integer>();
        in.readList(this.episodeRunTime, Integer.class.getClassLoader());
        this.firstAirDate = in.readString();
        this.genres = in.createTypedArrayList(GenreDomainModel.CREATOR);
        this.homepage = in.readString();
        this.id = in.readInt();
        this.inProduction = in.readByte() != 0;
        this.languages = in.createStringArrayList();
        this.lastAirDate = in.readString();
        this.name = in.readString();
        this.networks = in.createTypedArrayList(NetworkDomainModel.CREATOR);
        this.numberOfEpisodes = in.readInt();
        this.numberOfSeasons = in.readInt();
        this.originCountry = in.createStringArrayList();
        this.originalLanguage = in.readString();
        this.originalName = in.readString();
        this.overview = in.readString();
        this.popularity = in.readFloat();
        this.posterPath = in.readString();
        this.status = in.readString();
        this.type = in.readString();
        this.voteAverage = in.readFloat();
        this.voteCount = in.readInt();
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

    public List<GenreDomainModel> getGenres() {
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

    public List<String> getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public String getName() {
        return name;
    }

    public List<NetworkDomainModel> getNetworks() {
        return networks;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
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

    public Palette getPosterPalette() {
        return posterPalette;
    }

    public String getFirstAirYear(){
        String firstAirYear = "";
        if (!TextUtils.isEmpty(firstAirDate)) {
            Calendar calendar = DateUtility.getCalendar(firstAirDate, PATTERN);
            firstAirYear = String.format("%d", calendar.get(Calendar.YEAR));
        }
        return firstAirYear;
    }

    public int getFirstAirDateYear(){
        int firstAirDateYear = -1;
        if (!TextUtils.isEmpty(firstAirDate)) {
            Calendar calendar = DateUtility.getCalendar(firstAirDate, PATTERN);
            firstAirDateYear = calendar.get(Calendar.YEAR);
        }
        return firstAirDateYear;
    }

    public String getPosterUrl(){
        String profileUrl = String.format("%s%s%s", SECURE_BASE_URL, POSTER_SIZE, posterPath);
        return profileUrl;
    }

    public String getFormattedNetwork(){
        String formattedNetwork =  "";
        if(networks != null && networks.size()>0){

            NetworkDomainModel network = networks.get(0);
            formattedNetwork = network.getName();

            if(formattedNetwork.equals("Fox Broadcasting Company") || formattedNetwork.equals("Fox")){
                formattedNetwork = "FOX";
            } else if(formattedNetwork.equals("American Broadcasting Company")){
                formattedNetwork = "ABC";
            } else if(formattedNetwork.equals("The WB Television NetworkResponse")){
                formattedNetwork = "The WB";
            } else if(formattedNetwork.equals("National Educational Television")){
                formattedNetwork = "NET";
            } else if(formattedNetwork.equals("CBC Television")){
                formattedNetwork = "CBC";
            } else if(formattedNetwork.equals("British Broadcasting Corporation")){
                formattedNetwork = "BBC";
            } else if(formattedNetwork.equals("Lifetime Television")){
                formattedNetwork = "Lifetime";
            } else if(formattedNetwork.equals("Public Broadcasting Service")){
                formattedNetwork = "PBS";
            } else if(formattedNetwork.equals("Oprah Winfrey NetworkResponse")){
                formattedNetwork = "OWN";
            } else if(formattedNetwork.equals("The History Channel")){
                formattedNetwork = "History";
            } else if(formattedNetwork.equals("Orion Cinema NetworkResponse")){
                formattedNetwork = "OCN";
            } else if(formattedNetwork.equals("National Geographic Channel")){
                formattedNetwork = "National Geographic";
            } else if(formattedNetwork.equals("Seoul Broadcasting System")){
                formattedNetwork = "SBS";
            } else if(formattedNetwork.equals("Total Variety NetworkResponse")){
                formattedNetwork = "TVN";
            } else if(formattedNetwork.equals("Canal de las Estrellas")){
                formattedNetwork = "Las Estrellas";
            } else if(formattedNetwork.equals("Black Entertainment Television")){
                formattedNetwork = "BET";
            } else if(formattedNetwork.equals("RTL Television")){
                formattedNetwork = "RTL";
            } else if(formattedNetwork.equals("Munhwa Broadcasting Corporation")){
                formattedNetwork = "MBC";
            } else if(formattedNetwork.equals("Mainichi Broadcasting System")){
                formattedNetwork = "MBS";
            }
        }
        return formattedNetwork;
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

    public void setGenres(List<GenreDomainModel> genres) {
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

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetworks(List<NetworkDomainModel> networks) {
        this.networks = networks;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
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
        dest.writeInt(this.id);
        dest.writeByte(this.inProduction ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.languages);
        dest.writeString(this.lastAirDate);
        dest.writeString(this.name);
        dest.writeTypedList(this.networks);
        dest.writeInt(this.numberOfEpisodes);
        dest.writeInt(this.numberOfSeasons);
        dest.writeStringList(this.originCountry);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalName);
        dest.writeString(this.overview);
        dest.writeFloat(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeFloat(this.voteAverage);
        dest.writeInt(this.voteCount);
    }
    // endregion

    public static final Creator<TelevisionShowDomainModel> CREATOR = new Creator<TelevisionShowDomainModel>() {
        @Override
        public TelevisionShowDomainModel createFromParcel(Parcel source) {
            return new TelevisionShowDomainModel(source);
        }

        @Override
        public TelevisionShowDomainModel[] newArray(int size) {
            return new TelevisionShowDomainModel[size];
        }
    };

    @Override
    public String toString() {
        return "TelevisionShowPresentationModel{" +
                "backdropPath='" + backdropPath + '\'' +
                ", episodeRunTime=" + episodeRunTime +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", inProduction=" + inProduction +
                ", languages=" + languages +
                ", lastAirDate='" + lastAirDate + '\'' +
                ", name='" + name + '\'' +
                ", networks=" + networks +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", numberOfSeasons=" + numberOfSeasons +
                ", originCountry=" + originCountry +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalName='" + originalName + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", posterPalette=" + posterPalette +
                '}';
    }
}
