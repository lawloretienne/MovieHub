package com.etiennelawlor.moviehub.presentation.persons;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsDataModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface PersonsUiContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void setErrorText(String errorText);
        void showLoadingView();
        void hideLoadingView();
        void addHeader();
        void addFooter();
        void removeFooter();
        void showErrorFooter();
        void showLoadingFooter();
        void addPersonsToAdapter(List<Person> persons);
        void loadMoreItems();
        void setPersonsDataModel(PersonsDataModel personsDataModel);

        // Navigation methods
        void openPersonDetails(Person person);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularPersons(int currentPage);
        void onPersonClick(Person person);
        void onScrollToEndOfList();
    }
}
