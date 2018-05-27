package com.etiennelawlor.moviehub.domain.composers;

import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;
import com.etiennelawlor.moviehub.domain.mappers.MovieDomainModelMapper;
import com.etiennelawlor.moviehub.domain.mappers.PersonDomainModelMapper;
import com.etiennelawlor.moviehub.domain.mappers.TelevisionShowDomainModelMapper;
import com.etiennelawlor.moviehub.domain.models.SearchDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 1/1/18.
 */

public class SearchDomainModelComposer {

    private MovieDomainModelMapper movieDomainModelMapper = new MovieDomainModelMapper();
    private PersonDomainModelMapper personDomainModelMapper = new PersonDomainModelMapper();
    private TelevisionShowDomainModelMapper televisionShowDomainModelMapper = new TelevisionShowDomainModelMapper();

    public SearchDomainModel compose(MoviesDataModel moviesDataModel, TelevisionShowsDataModel televisionShowsDataModel, PersonsDataModel personsDataModel, String query){
        SearchDomainModel searchDomainModel = new SearchDomainModel();

        List<MovieDataModel> movies = new ArrayList<>();
        List<TelevisionShowDataModel> televisionShows = new ArrayList<>();
        List<PersonDataModel> persons = new ArrayList<>();

        if (moviesDataModel != null) {
            movies = moviesDataModel.getMovies();
        }

        if (televisionShowsDataModel != null) {
            televisionShows = televisionShowsDataModel.getTelevisionShows();
        }

        if (personsDataModel != null) {
            persons = personsDataModel.getPersons();
        }

        searchDomainModel.setMovies(movieDomainModelMapper.mapListToDomainModelList(movies));
        searchDomainModel.setPersons(personDomainModelMapper.mapListToDomainModelList(persons));
        searchDomainModel.setQuery(query);
        searchDomainModel.setTelevisionShows(televisionShowDomainModelMapper.mapListToDomainModelList(televisionShows));

        return searchDomainModel;
    }
}
