package com.etiennelawlor.moviehub.data.remote.response;

import android.support.v7.graphics.Palette;
import android.text.TextUtils;

import com.etiennelawlor.moviehub.util.DateUtility;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by etiennelawlor on 12/16/16.
 */

public class PersonCredit extends Credit {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    public static final String SECURE_BASE_URL = "https://image.tmdb.org/t/p/";
    public static final String POSTER_SIZE = "w780";
    // endregion

    // region Fields
    @SerializedName("job")
    public String job;
    @SerializedName("character")
    public String character;
    @SerializedName("title")
    public String title;
    @SerializedName("name")
    public String name;
    @SerializedName("department")
    public String department;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("first_air_date")
    public String firstAirDate;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("media_type")
    public String mediaType;

    private Palette posterPalette;
    // endregion

    // region Getters

    public String getJob() {
        return job;
    }

    public String getCharacter() {
        return character;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Palette getPosterPalette() {
        return posterPalette;
    }

    public String getPosterUrl(){
        String profileUrl = String.format("%s%s%s", SECURE_BASE_URL, POSTER_SIZE, posterPath);
        return profileUrl;
    }

    public int getFirstAirYear(){
        int firstAirYear = -1;
        if (!TextUtils.isEmpty(firstAirDate)) {
            Calendar calendar = DateUtility.getCalendar(firstAirDate, PATTERN);
            firstAirYear = calendar.get(Calendar.YEAR);
        }
        return firstAirYear;
    }

    public int getReleaseYear(){
        int releaseYear = -1;
        if (!TextUtils.isEmpty(releaseDate)) {
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);
            releaseYear = calendar.get(Calendar.YEAR);
        }
        return releaseYear;
    }
    // endregion

    // region Setters

    public void setJob(String job) {
        this.job = job;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setPosterPalette(Palette posterPalette) {
        this.posterPalette = posterPalette;
    }

    // endregion

    @Override
    public String toString() {
        return "PersonCredit{" +
                "job='" + job + '\'' +
                ", character='" + character + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", posterPalette=" + posterPalette +
                '}';
    }
}
