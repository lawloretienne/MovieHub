package com.etiennelawlor.moviehub.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.etiennelawlor.moviehub.network.models.Configuration;
import com.google.gson.Gson;

/**
 * Created by etiennelawlor on 12/17/16.
 */

public class MovieHubPrefs {

    // region Constants
    private static final String MOVIE_HUB_PREF = "MOVIE_HUB_PREF";
    private static final String KEY_CONFIGURATION = "KEY_CONFIGURATION";
    // endregion

    // region Constructors
    private MovieHubPrefs() {
        //no instance
    }
    // endregion

    // region Getters
    public static Configuration getConfiguration(Context context){
        SharedPreferences preferences = getSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(KEY_CONFIGURATION, "");
        Configuration configuration = gson.fromJson(json, Configuration.class);
        return configuration;
    }
    // endregion

    // region Setters
    public static void setConfiguration(Context context, Configuration configuration) {
        SharedPreferences.Editor editor = getEditor(context);
        Gson gson = new Gson();
        String json = gson.toJson(configuration);
        editor.putString(KEY_CONFIGURATION, json)
                .apply();
    }
    // endregion

    // region Helper Methods
    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MOVIE_HUB_PREF, Context.MODE_PRIVATE);
    }
    // endregion

}
