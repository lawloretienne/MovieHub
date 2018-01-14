package com.etiennelawlor.moviehub.presentation.persons;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;
import com.etiennelawlor.moviehub.presentation.base.BasePresenter;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;

import java.util.List;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface PersonsPresentationContract {

    interface View {
        void showEmptyView();
        void hideEmptyView();
        void showErrorView();
        void hideErrorView();
        void showLoadingView();
        void hideLoadingView();
        void addHeaderView();
        void addFooterView();
        void removeFooterView();
        void showErrorFooterView();
        void showLoadingFooterView();
        void showPersons(List<PersonDomainModel> persons);
        void loadMorePersons();
        void setPersonsDomainModel(PersonsDomainModel personsDomainModel);

        // Navigation methods
        void openPersonDetails(PersonPresentationModel person);
    }

    interface Presenter extends BasePresenter {
        void onLoadPopularPersons(int currentPage);
        void onPersonClick(PersonPresentationModel person);
        void onScrollToEndOfList();
    }
}
