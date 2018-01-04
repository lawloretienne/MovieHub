package com.etiennelawlor.moviehub.domain.models;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class TelevisionShowCreditDomainModel extends CreditDomainModel {

    // region Fields
    public String job;
    public String character;
    public String name;
    public String department;
    public String profilePath;
    // endregion

    // region Getters

    public String getJob() {
        return job;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getProfilePath() {
        return profilePath;
    }

    // endregion

    // region Setters

    public void setJob(String job) {
        this.job = job;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    // endregion

    @Override
    public String toString() {
        return "TelevisionShowCreditDomainModel{" +
                "job='" + job + '\'' +
                ", character='" + character + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
