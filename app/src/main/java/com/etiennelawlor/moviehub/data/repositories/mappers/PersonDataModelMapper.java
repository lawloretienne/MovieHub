package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class PersonDataModelMapper implements DataModelMapper<PersonResponse, PersonDataModel> {

    // region Member Variables
    private ProfileImagesDataModelMapper profileImagesDataModelMapper = new ProfileImagesDataModelMapper();
    // endregion

    @Override
    public PersonDataModel mapToDataModel(PersonResponse personResponse) {
        PersonDataModel personDataModel = new PersonDataModel();
        personDataModel.setBiography(personResponse.getBiography());
        personDataModel.setBirthday(personResponse.getBirthday());
        personDataModel.setDeathday(personResponse.getDeathday());
        personDataModel.setId(personResponse.getId());
        personDataModel.setImages(profileImagesDataModelMapper.mapToDataModel(personResponse.getImages()));
        personDataModel.setImdbId(personResponse.getImdbId());
        personDataModel.setName(personResponse.getName());
        personDataModel.setPlaceOfBirth(personResponse.getPlaceOfBirth());
        personDataModel.setProfilePath(personResponse.getProfilePath());

        return personDataModel;
    }
}
