package com.etiennelawlor.moviehub.models;

import com.etiennelawlor.moviehub.network.models.TelevisionShow;
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
    // endregion

    // region Constructors

    public FullTelevisionShow(TelevisionShow televisionShow, TelevisionShowCreditsEnvelope televisionShowCreditsEnvelope, TelevisionShowsEnvelope televisionShowsEnvelope) {
        this.televisionShow = televisionShow;
        this.televisionShowCreditsEnvelope = televisionShowCreditsEnvelope;
        this.televisionShowsEnvelope = televisionShowsEnvelope;
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

    // endregion
}
