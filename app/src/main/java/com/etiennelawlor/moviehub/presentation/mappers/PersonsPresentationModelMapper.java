package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;
import com.etiennelawlor.moviehub.presentation.models.PersonsPresentationModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class PersonsPresentationModelMapper implements PresentationModelMapper<PersonsDomainModel, PersonsPresentationModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private PersonPresentationModelMapper personPresentationModelMapper = new PersonPresentationModelMapper();
    // endregion

    @Override
    public PersonsPresentationModel mapToPresentationModel(PersonsDomainModel personsDomainModel) {
        PersonsPresentationModel personsPresentationModel = new PersonsPresentationModel();
        List<PersonDomainModel> personDomainModels = personsDomainModel.getPersons();
        personsPresentationModel.setLastPage(personDomainModels.size() < PAGE_SIZE ? true : false);
        personsPresentationModel.setPageNumber(personsDomainModel.getPageNumber());
        personsPresentationModel.setPersons(personPresentationModelMapper.mapListToPresentationModelList(personsDomainModel.getPersons()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        personsPresentationModel.setExpiredAt(calendar.getTime());
        return personsPresentationModel;
    }
}
