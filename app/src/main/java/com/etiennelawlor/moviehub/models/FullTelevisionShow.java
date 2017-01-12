package com.etiennelawlor.moviehub.models;

import com.etiennelawlor.moviehub.network.models.TelevisionShow;
import com.etiennelawlor.moviehub.network.models.TelevisionShowContentRatingsEnvelope;
import com.etiennelawlor.moviehub.network.models.TelevisionShowCreditsEnvelope;
import com.etiennelawlor.moviehub.network.models.TelevisionShowsEnvelope;

/**
 * Created by etiennelawlor on 12/19/16.
 */

public class FullTelevisionShow {

    // region Member Variables
    private TelevisionShow televisionShow;
    private TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope;
    private TelevisionShowsEnvelope televisionShowsEnvelope;
    private TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope;
    // endregion

    // region Constructors

    public FullTelevisionShow(TelevisionShow televisionShow, TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope, TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope) {
        this.televisionShow = televisionShow;
        this.televisionShowCreditsEnvelope = televisionShowCreditsEnvelope;
        this.televisionShowsEnvelope = televisionShowsEnvelope;
        this.televisionShowContentRatingsEnvelope = televisionShowContentRatingsEnvelope;
    }

    // endregion

    // region Getters

    public TelevisionShow getTelevisionShow() {
        return televisionShow;
    }

    public TelevisionShowCreditsEnvelope getTelevisionShowCreditsEnvelope() {
        return televisionShowCreditsEnvelope;
    }

    public TelevisionShowsEnvelope getTelevisionShowsEnvelope() {
        return televisionShowsEnvelope;
    }

    public TelevisionShowContentRatingsEnvelope getTelevisionShowContentRatingsEnvelope() {
        return televisionShowContentRatingsEnvelope;
    }

    // endregion

    // region Setters

    public void setTelevisionShow(TelevisionShow televisionShow) {
        this.televisionShow = televisionShow;
    }

    public void setTelevisionShowCreditsEnvelope(TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope) {
        this.televisionShowCreditsEnvelope = televisionShowCreditsEnvelope;
    }

    public void setTelevisionShowsEnvelope(TelevisionShowsEnvelope televisionShowsEnvelope) {
        this.televisionShowsEnvelope = televisionShowsEnvelope;
    }

    public void setTelevisionShowContentRatingsEnvelope(TelevisionShowContentRatingsEnvelope televisionShowContentRatingsEnvelope) {
        this.televisionShowContentRatingsEnvelope = televisionShowContentRatingsEnvelope;
    }

    // endregion
}
