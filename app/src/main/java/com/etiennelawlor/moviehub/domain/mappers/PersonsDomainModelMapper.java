package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class PersonsDomainModelMapper implements DomainModelMapper<PersonsDataModel, PersonsDomainModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    // region Member Variables
    private PersonDomainModelMapper personDomainModelMapper = new PersonDomainModelMapper();
    // endregion

    @Override
    public PersonsDomainModel mapToDomainModel(PersonsDataModel personsDataModel) {
        PersonsDomainModel personsDomainModel = new PersonsDomainModel();
        List<PersonDataModel> personDataModels = personsDataModel.getPersons();
        personsDomainModel.setLastPage(personDataModels.size() < PAGE_SIZE ? true : false);
        personsDomainModel.setPageNumber(personsDataModel.getPageNumber());
        personsDomainModel.setPersons(personDomainModelMapper.mapListToDomainModelList(personsDataModel.getPersons()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        personsDomainModel.setExpiredAt(calendar.getTime());
        return personsDomainModel;
    }
}
