package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class PersonPresentationModelMapper implements PresentationModelMapper<PersonDomainModel, PersonPresentationModel>, PresentationModelListMapper<PersonDomainModel, PersonPresentationModel> {

    // region Member Variables
    private ProfileImagesPresentationModelMapper profileImagesPresentationModelMapper = new ProfileImagesPresentationModelMapper();
    // endregion

    @Override
    public PersonPresentationModel mapToPresentationModel(PersonDomainModel personDomainModel) {
        PersonPresentationModel personPresentationModel = new PersonPresentationModel();
        personPresentationModel.setBiography(personDomainModel.getBiography());
        personPresentationModel.setBirthday(personDomainModel.getBirthday());
        personPresentationModel.setDeathday(personDomainModel.getDeathday());
        personPresentationModel.setId(personDomainModel.getId());
        personPresentationModel.setImages(profileImagesPresentationModelMapper.mapToPresentationModel(personDomainModel.getImages()));
        personPresentationModel.setImdbId(personDomainModel.getImdbId());
        personPresentationModel.setName(personDomainModel.getName());
        personPresentationModel.setPlaceOfBirth(personDomainModel.getPlaceOfBirth());
        personPresentationModel.setProfilePath(personDomainModel.getProfilePath());

        return personPresentationModel;
    }

    @Override
    public List<PersonPresentationModel> mapListToPresentationModelList(List<PersonDomainModel> personDomainModels) {
        List<PersonPresentationModel> personPresentationModels = new ArrayList<>();
        if(personDomainModels != null && personDomainModels.size()>0) {
            for (PersonDomainModel personDomainModel : personDomainModels) {
                personPresentationModels.add(mapToPresentationModel(personDomainModel));
            }
        }
        return personPresentationModels;
    }
}
