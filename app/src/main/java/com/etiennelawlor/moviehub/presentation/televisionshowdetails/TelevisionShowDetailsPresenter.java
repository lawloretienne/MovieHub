package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.domain.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowDetailsPresenter implements TelevisionShowDetailsUiContract.Presenter {

    // region Member Variables
    private final TelevisionShowDetailsUiContract.View televisionShowDetailsView;
    private final TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase;
    // endregion

    // region Constructors

    public TelevisionShowDetailsPresenter(TelevisionShowDetailsUiContract.View televisionShowDetailsView, TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase) {
        this.televisionShowDetailsView = televisionShowDetailsView;
        this.televisionShowDetailsUseCase = televisionShowDetailsUseCase;
    }

    // endregion

    // region TelevisionShowDetailsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        televisionShowDetailsUseCase.clearSubscriptions();
    }

    @Override
    public void onLoadTelevisionShowDetails(int televisionShowId) {
        televisionShowDetailsUseCase.getTelevisionShowDetails(televisionShowId, new Subscriber<TelevisionShowDetailsWrapper>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                    televisionShowDetailsView.showErrorView();
                }
            }

            @Override
            public void onNext(TelevisionShowDetailsWrapper televisionShowDetailsWrapper) {
                if(televisionShowDetailsWrapper != null){
                    televisionShowDetailsView.showTelevisionShowDetails(televisionShowDetailsWrapper);
                }
            }
        });
    }

    @Override
    public void onPersonClick(Person person) {
        televisionShowDetailsView.openPersonDetails(person);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShow televisionShow) {
        televisionShowDetailsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            televisionShowDetailsView.showToolbarTitle();
        else
            televisionShowDetailsView.hideToolbarTitle();
    }

    // endregion
}
