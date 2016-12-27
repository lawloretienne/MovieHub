package com.etiennelawlor.moviehub.utilities;

import android.content.Context;

import com.etiennelawlor.moviehub.network.models.Configuration;
import com.etiennelawlor.moviehub.network.models.Images;
import com.etiennelawlor.moviehub.prefs.MovieHubPrefs;

import java.util.List;

/**
 * Created by etiennelawlor on 12/27/16.
 */

public class ConfigurationUtility {

    public static String getSecureBaseUrl(Context context){
        String secureBaseUrl = "";

        Configuration configuration = MovieHubPrefs.getConfiguration(context);
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {
                secureBaseUrl = images.getSecureBaseUrl();
            }
        }

        return secureBaseUrl;
    }

    public static String getProfileSize(Context context){
        String profileSize = "";

        Configuration configuration = MovieHubPrefs.getConfiguration(context);
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {

                List<String> profileSizes = images.getProfileSizes();
                if (profileSizes != null && profileSizes.size() > 0) {
                    if (profileSizes.size() > 1) {
                        profileSize = profileSizes.get(profileSizes.size() - 2);
                    } else {
                        profileSize = profileSizes.get(profileSizes.size() - 1);
                    }
                }
            }
        }

        return profileSize;
    }

    public static String getPosterSize(Context context){
        String posterSize = "";

        Configuration configuration = MovieHubPrefs.getConfiguration(context);
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {

                List<String> posterSizes = images.getPosterSizes();
                if (posterSizes != null && posterSizes.size() > 0) {
                    if (posterSizes.size() > 1) {
                        posterSize = posterSizes.get(posterSizes.size() - 2);
                    } else {
                        posterSize = posterSizes.get(posterSizes.size() - 1);
                    }
                }
            }
        }

        return posterSize;
    }
}
