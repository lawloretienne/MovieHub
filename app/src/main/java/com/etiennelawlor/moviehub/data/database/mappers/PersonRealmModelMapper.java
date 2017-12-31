package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.PersonRealmModel;
import com.etiennelawlor.moviehub.data.network.response.Person;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class PersonRealmModelMapper implements RealmModelMapper<Person, PersonRealmModel> {

    private ProfileImagesRealmModelMapper profileImagesRealmMapper = new ProfileImagesRealmModelMapper();

    @Override
    public PersonRealmModel mapToRealmModel(Person person) {
        PersonRealmModel realmPerson = Realm.getDefaultInstance().createObject(PersonRealmModel.class);

        realmPerson.setBiography(person.getBiography());
        realmPerson.setBirthday(person.getBirthday());
        realmPerson.setDeathday(person.getDeathday());
        realmPerson.setId(person.getId());
        realmPerson.setImdbId(person.getImdbId());
        realmPerson.setName(person.getName());
        realmPerson.setPlaceOfBirth(person.getPlaceOfBirth());
        realmPerson.setProfilePath(person.getProfilePath());
        realmPerson.setImages(profileImagesRealmMapper.mapToRealmModel(person.getImages()));

        return realmPerson;
    }

    @Override
    public Person mapFromRealmModel(PersonRealmModel personRealmModel) {
        Person person = new Person();

        person.setBiography(personRealmModel.getBiography());
        person.setBirthday(personRealmModel.getBirthday());
        person.setDeathday(personRealmModel.getDeathday());
        person.setId(personRealmModel.getId());
        person.setImdbId(personRealmModel.getImdbId());
        person.setName(personRealmModel.getName());
        person.setPlaceOfBirth(personRealmModel.getPlaceOfBirth());
        person.setProfilePath(personRealmModel.getProfilePath());
        person.setImages(profileImagesRealmMapper.mapFromRealmModel(personRealmModel.getImages()));

        return person;
    }
}
