package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.PersonRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class PersonRealmModelMapper implements RealmModelMapper<PersonDataModel, PersonRealmModel> {

    private ProfileImagesRealmModelMapper profileImagesRealmMapper = new ProfileImagesRealmModelMapper();

    @Override
    public PersonRealmModel mapToRealmModel(PersonDataModel personDataModel) {
        PersonRealmModel realmPerson = Realm.getDefaultInstance().createObject(PersonRealmModel.class);

        realmPerson.setBiography(personDataModel.getBiography());
        realmPerson.setBirthday(personDataModel.getBirthday());
        realmPerson.setDeathday(personDataModel.getDeathday());
        realmPerson.setId(personDataModel.getId());
        realmPerson.setImdbId(personDataModel.getImdbId());
        realmPerson.setName(personDataModel.getName());
        realmPerson.setPlaceOfBirth(personDataModel.getPlaceOfBirth());
        realmPerson.setProfilePath(personDataModel.getProfilePath());
        realmPerson.setImages(profileImagesRealmMapper.mapToRealmModel(personDataModel.getImages()));

        return realmPerson;
    }

    @Override
    public PersonDataModel mapFromRealmModel(PersonRealmModel personRealmModel) {
        PersonDataModel personDataModel = new PersonDataModel();

        personDataModel.setBiography(personRealmModel.getBiography());
        personDataModel.setBirthday(personRealmModel.getBirthday());
        personDataModel.setDeathday(personRealmModel.getDeathday());
        personDataModel.setId(personRealmModel.getId());
        personDataModel.setImdbId(personRealmModel.getImdbId());
        personDataModel.setName(personRealmModel.getName());
        personDataModel.setPlaceOfBirth(personRealmModel.getPlaceOfBirth());
        personDataModel.setProfilePath(personRealmModel.getProfilePath());
        personDataModel.setImages(profileImagesRealmMapper.mapFromRealmModel(personRealmModel.getImages()));

        return personDataModel;
    }
}
