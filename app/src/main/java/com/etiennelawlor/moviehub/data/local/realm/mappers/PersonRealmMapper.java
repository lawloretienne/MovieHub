package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmPerson;
import com.etiennelawlor.moviehub.data.remote.response.Person;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class PersonRealmMapper implements RealmMapper<Person, RealmPerson> {

    private ProfileImagesRealmMapper profileImagesRealmMapper = new ProfileImagesRealmMapper();

    @Override
    public RealmPerson mapToRealmObject(Person person) {
        RealmPerson realmPerson = Realm.getDefaultInstance().createObject(RealmPerson.class);

        realmPerson.setBiography(person.getBiography());
        realmPerson.setBirthday(person.getBirthday());
        realmPerson.setDeathday(person.getDeathday());
        realmPerson.setId(person.getId());
        realmPerson.setImdbId(person.getImdbId());
        realmPerson.setName(person.getName());
        realmPerson.setPlaceOfBirth(person.getPlaceOfBirth());
        realmPerson.setProfilePath(person.getProfilePath());
        realmPerson.setImages(profileImagesRealmMapper.mapToRealmObject(person.getImages()));

        return realmPerson;
    }

    @Override
    public Person mapFromRealmObject(RealmPerson realmPerson) {
        Person person = new Person();

        person.setBiography(realmPerson.getBiography());
        person.setBirthday(realmPerson.getBirthday());
        person.setDeathday(realmPerson.getDeathday());
        person.setId(realmPerson.getId());
        person.setImdbId(realmPerson.getImdbId());
        person.setName(realmPerson.getName());
        person.setPlaceOfBirth(realmPerson.getPlaceOfBirth());
        person.setProfilePath(realmPerson.getProfilePath());
        person.setImages(profileImagesRealmMapper.mapFromRealmObject(realmPerson.getImages()));

        return person;
    }
}
