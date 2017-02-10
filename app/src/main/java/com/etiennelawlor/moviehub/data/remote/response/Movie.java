package com.etiennelawlor.moviehub.data.remote.response;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;

import com.etiennelawlor.moviehub.util.ConfigurationUtility;
import com.etiennelawlor.moviehub.util.DateUtility;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class Movie implements Parcelable {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    // endregion

    // region Fields
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("budget")
    public int budget;
    @SerializedName("genres")
    public List<Genre> genres = null;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public float popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("revenue")
    public long revenue;
    @SerializedName("runtime")
    public int runtime;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public float voteAverage;
    @SerializedName("vote_count")
    public int voteCount;

    private Palette posterPalette;
    // endregion

    // region Constructors
    public Movie() {
    }

    protected Movie(Parcel in) {
        this.adult = in.readByte() != 0;
        this.backdropPath = in.readString();
        this.budget = in.readInt();
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.homepage = in.readString();
        this.id = in.readInt();
        this.imdbId = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.overview = in.readString();
        this.popularity = in.readFloat();
        this.posterPath = in.readString();
        this.releaseDate = in.readString();
        this.revenue = in.readLong();
        this.runtime = in.readInt();
        this.status = in.readString();
        this.tagline = in.readString();
        this.title = in.readString();
        this.video = in.readByte() != 0;
        this.voteAverage = in.readFloat();
        this.voteCount = in.readInt();
    }
    // endregion

    // region Getters

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
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

    public String getReleaseYear(){
        String releaseYear = "";
        if (!TextUtils.isEmpty(releaseDate)) {
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);
            releaseYear = String.format("%d", calendar.get(Calendar.YEAR));
        }
        return releaseYear;
    }

    public int getReleaseDateYear(){
        int releaseDateYear = -1;
        if (!TextUtils.isEmpty(releaseDate)) {
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);
            releaseDateYear = calendar.get(Calendar.YEAR);
        }
        return releaseDateYear;
    }

    public String getPosterUrl(Context context){
        String secureBaseUrl = ConfigurationUtility.getSecureBaseUrl(context);
        String posterSize = ConfigurationUtility.getPosterSize(context);
        String profileUrl = String.format("%s%s%s", secureBaseUrl, posterSize, posterPath);

        return profileUrl;
    }

    // endregion

    // region Setters

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(boolean video) {
        this.video = video;
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
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdropPath);
        dest.writeInt(this.budget);
        dest.writeTypedList(this.genres);
        dest.writeString(this.homepage);
        dest.writeInt(this.id);
        dest.writeString(this.imdbId);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeString(this.overview);
        dest.writeFloat(this.popularity);
        dest.writeString(this.posterPath);
        dest.writeString(this.releaseDate);
        dest.writeLong(this.revenue);
        dest.writeInt(this.runtime);
        dest.writeString(this.status);
        dest.writeString(this.tagline);
        dest.writeString(this.title);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.voteAverage);
        dest.writeInt(this.voteCount);
    }
    // endregion

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
