package com.etiennelawlor.moviehub.data.database.models;

import io.realm.RealmObject;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class StringRealmModel extends RealmObject {

    // region Fields
    public String value;
    // endregion

    // region Getters
    public String getValue() {
        return value;
    }
    // endregion

    // region Setters
    public void setValue(String value) {
        this.value = value;
    }
    // endregion
}
