package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.PersonResponse;
import com.etiennelawlor.moviehub.data.network.response.PersonsResponse;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by etiennelawlor on 12/30/17.
 */

public class PersonsDataModelMapper implements DataModelMapper<PersonsResponse, PersonsDataModel> {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
    // endregion

    @Override
    public PersonsDataModel mapToDataModel(PersonsResponse personsResponse) {
        PersonsDataModel personsDataModel = new PersonsDataModel();

        List<PersonResponse> persons = personsResponse.getPersons();
        personsDataModel.setLastPage(persons.size() < PAGE_SIZE ? true : false);
        personsDataModel.setPageNumber(personsResponse.getPage());
        personsDataModel.setPersons(persons);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, SEVEN_DAYS);
        personsDataModel.setExpiredAt(calendar.getTime());

        return personsDataModel;
    }
}
