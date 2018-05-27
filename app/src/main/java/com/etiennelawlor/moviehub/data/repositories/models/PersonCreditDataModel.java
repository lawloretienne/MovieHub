package com.etiennelawlor.moviehub.data.repositories.models;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class PersonCreditDataModel extends CreditDataModel {

    // region Fields
    public String job;
    public String character;
    public String title;
    public String name;
    public String department;
    public String posterPath;
    public String firstAirDate;
    public String releaseDate;
    public String mediaType;
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

    // endregion

    @Override
    public String toString() {
        return "PersonCreditDataModel{" +
                "job='" + job + '\'' +
                ", character='" + character + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}
