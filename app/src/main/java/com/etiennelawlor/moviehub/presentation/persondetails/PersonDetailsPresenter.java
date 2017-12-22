package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.domain.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonDetailsPresenter implements PersonDetailsUiContract.Presenter {

    // region Member Variables
    private final PersonDetailsUiContract.View personDetailsView;
    private final PersonDetailsDomainContract.UseCase personDetailsUseCase;
    // endregion

    // region Constructors

    public PersonDetailsPresenter(PersonDetailsUiContract.View personDetailsView, PersonDetailsDomainContract.UseCase personDetailsUseCase) {
        this.personDetailsView = personDetailsView;
        this.personDetailsUseCase = personDetailsUseCase;
    }

    // endregion

    // region PersonDetailsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        personDetailsUseCase.clearSubscriptions();
    }

    @Override
    public void onLoadPersonDetails(int personId) {
        personDetailsUseCase.getPersonDetails(personId, new DisposableSingleObserver<PersonDetailsWrapper>() {
            @Override
            public void onSuccess(PersonDetailsWrapper personDetailsWrapper) {
                if(personDetailsWrapper != null){
                    personDetailsView.showPersonDetails(personDetailsWrapper);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                    personDetailsView.showErrorView();
                }
            }
        });
    }

    @Override
    public void onMovieClick(Movie movie) {
        personDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShow televisionShow) {
        personDetailsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            personDetailsView.showToolbarTitle();
        else
            personDetailsView.hideToolbarTitle();
    }

    // endregion
}
