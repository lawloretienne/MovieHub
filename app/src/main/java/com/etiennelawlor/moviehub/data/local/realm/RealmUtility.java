package com.etiennelawlor.moviehub.data.local.realm;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmConfiguration;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmImages;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmString;
import com.etiennelawlor.moviehub.data.remote.response.Configuration;
import com.etiennelawlor.moviehub.data.remote.response.Images;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by etiennelawlor on 11/22/15.
 */
public class RealmUtility {

    public static Configuration getCachedConfiguration() {
        Configuration configuration = new Configuration();

        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmConfiguration> realmResults
                    = realm.where(RealmConfiguration.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                RealmConfiguration realmConfiguration = realmResults.get(0);

                Images images = new Images();
                RealmImages realmImages = realmConfiguration.getImages();
                images.setBaseUrl(realmImages.getBaseUrl());
                images.setSecureBaseUrl(realmImages.getSecureBaseUrl());
                images.setBackdropSizes(getList(realmImages.getBackdropSizes()));
                images.setLogoSizes(getList(realmImages.getLogoSizes()));
                images.setPosterSizes(getList(realmImages.getPosterSizes()));
                images.setProfileSizes(getList(realmImages.getProfileSizes()));
                images.setStillSizes(getList(realmImages.getStillSizes()));

                configuration.setImages(images);

                List<String> changeKeys = new ArrayList<>();
                for(RealmString realmChangeKey : realmConfiguration.getChangeKeys()){
                    changeKeys.add(realmChangeKey.getValue());
                }

                configuration.setChangeKeys(changeKeys);
                return configuration;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void persistConfiguration(Configuration configuration){
        Realm realm = Realm.getDefaultInstance();
        try {
            Images images = configuration.getImages();
            List<String> changeKeys = configuration.getChangeKeys();

            realm.beginTransaction();

            realm.delete(RealmConfiguration.class);

            RealmConfiguration realmConfiguration =
                    realm.createObject(RealmConfiguration.class);

            RealmImages realmImages =
                    realm.createObject(RealmImages.class);

            realmImages.setBaseUrl(images.getBaseUrl());
            realmImages.setSecureBaseUrl(images.getSecureBaseUrl());
            realmImages.setBackdropSizes(getRealmList(realm, images.getBackdropSizes()));
            realmImages.setLogoSizes(getRealmList(realm, images.getLogoSizes()));
            realmImages.setPosterSizes(getRealmList(realm, images.getPosterSizes()));
            realmImages.setProfileSizes(getRealmList(realm, images.getProfileSizes()));
            realmImages.setStillSizes(getRealmList(realm, images.getStillSizes()));

            realmConfiguration.setImages(realmImages);

            RealmList<RealmString> realmChangeKeys = new RealmList<>();
            for (String changeKey : changeKeys) {
                RealmString realmChangeKey
                        = realm.createObject(RealmString.class);
                realmChangeKey.setValue(changeKey);
                realmChangeKeys.add(realmChangeKey);
            }

            realmConfiguration.setChangeKeys(realmChangeKeys);

            realm.copyToRealm(realmConfiguration);
            realm.commitTransaction();
        } finally {
            realm.close();
        }
    }

    private static RealmList<RealmString> getRealmList(Realm realm, List<String> stringList){
        RealmList<RealmString> realmStrings = new RealmList<>();
        for(String s : stringList){
            RealmString realmString
                    = realm.createObject(RealmString.class);
            realmString.setValue(s);
            realmStrings.add(realmString);
        }

        return realmStrings;
    }

    private static List<String> getList(RealmList<RealmString> realmStrings){
        List<String> strings = new ArrayList<>();
        for(RealmString realmString : realmStrings){
            strings.add(realmString.getValue());
        }
        return strings;
    }
}
