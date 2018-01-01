package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class PersonDomainModelMapper implements DomainModelMapper<PersonDataModel, PersonDomainModel>, DomainModelListMapper<PersonDataModel, PersonDomainModel> {

    // region Member Variables
    private ProfileImagesDomainModelMapper profileImagesDomainModelMapper = new ProfileImagesDomainModelMapper();
    // endregion

    @Override
    public PersonDomainModel mapToDomainModel(PersonDataModel personDataModel) {
        PersonDomainModel personDomainModel = new PersonDomainModel();
        personDomainModel.setBiography(personDataModel.getBiography());
        personDomainModel.setBirthday(personDataModel.getBirthday());
        personDomainModel.setDeathday(personDataModel.getDeathday());
        personDomainModel.setId(personDataModel.getId());
        personDomainModel.setImages(profileImagesDomainModelMapper.mapToDomainModel(personDataModel.getImages()));
        personDomainModel.setImdbId(personDataModel.getImdbId());
        personDomainModel.setName(personDataModel.getName());
        personDomainModel.setPlaceOfBirth(personDataModel.getPlaceOfBirth());
        personDomainModel.setProfilePath(personDataModel.getProfilePath());

        return personDomainModel;
    }

    @Override
    public List<PersonDomainModel> mapListToDomainModelList(List<PersonDataModel> personDataModels) {
        List<PersonDomainModel> personDomainModels = new ArrayList<>();
        if(personDataModels != null && personDataModels.size()>0) {
            for (PersonDataModel personDataModel : personDataModels) {
                personDomainModels.add(mapToDomainModel(personDataModel));
            }
        }
        return personDomainModels;
    }
}
