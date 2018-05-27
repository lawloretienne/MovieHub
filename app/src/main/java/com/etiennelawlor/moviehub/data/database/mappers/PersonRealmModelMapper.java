package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.PersonRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class PersonRealmModelMapper implements RealmModelMapper<PersonDataModel, PersonRealmModel>, RealmModelListMapper<PersonDataModel, PersonRealmModel> {

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

    @Override
    public RealmList<PersonRealmModel> mapListToRealmModelList(List<PersonDataModel> personDataModels) {
        RealmList<PersonRealmModel> personRealmModels = new RealmList<>();
        for(PersonDataModel personDataModel : personDataModels){
            personRealmModels.add(mapToRealmModel(personDataModel));
        }
        return personRealmModels;
    }

    @Override
    public List<PersonDataModel> mapListFromRealmModelList(RealmList<PersonRealmModel> personRealmModels) {
        List<PersonDataModel> personDataModels = new ArrayList<>();
        for(PersonRealmModel personRealmModel : personRealmModels){
            personDataModels.add(mapFromRealmModel(personRealmModel));
        }
        return personDataModels;
    }
}
