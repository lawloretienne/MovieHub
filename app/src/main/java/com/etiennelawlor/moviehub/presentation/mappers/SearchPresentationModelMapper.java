package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;
import com.etiennelawlor.moviehub.presentation.models.SearchPresentationModel;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class SearchPresentationModelMapper implements PresentationModelMapper<SearchDomainModel, SearchPresentationModel> {

    // region Member Variables
    private MoviePresentationModelMapper moviePresentationModelMapper = new MoviePresentationModelMapper();
    private PersonPresentationModelMapper personPresentationModelMapper = new PersonPresentationModelMapper();
    private TelevisionShowPresentationModelMapper televisionShowPresentationModelMapper = new TelevisionShowPresentationModelMapper();
    // endregion

    @Override
    public SearchPresentationModel mapToPresentationModel(SearchDomainModel searchDomainModel) {
        SearchPresentationModel searchPresentationModel = new SearchPresentationModel();
        searchPresentationModel.setMovies(moviePresentationModelMapper.mapListToPresentationModelList(searchDomainModel.getMovies()));
        searchPresentationModel.setPersons(personPresentationModelMapper.mapListToPresentationModelList(searchDomainModel.getPersons()));
        searchPresentationModel.setQuery(searchDomainModel.getQuery());
        searchPresentationModel.setTelevisionShows(televisionShowPresentationModelMapper.mapListToPresentationModelList(searchDomainModel.getTelevisionShows()));

        return searchPresentationModel;
    }
}
