package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.ProfileImageRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImageDataModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImageRealmModelMapper implements RealmModelMapper<ProfileImageDataModel, ProfileImageRealmModel> {

    @Override
    public ProfileImageRealmModel mapToRealmModel(ProfileImageDataModel profileImageDataModel) {
        ProfileImageRealmModel realmProfileImage = Realm.getDefaultInstance().createObject(ProfileImageRealmModel.class);

        realmProfileImage.setAspectRatio(profileImageDataModel.getAspectRatio());
        realmProfileImage.setFilePath(profileImageDataModel.getFilePath());
        realmProfileImage.setHeight(profileImageDataModel.getHeight());
        realmProfileImage.setVoteAverage(profileImageDataModel.getVoteAverage());
        realmProfileImage.setVoteCount(profileImageDataModel.getVoteCount());
        realmProfileImage.setWidth(profileImageDataModel.getWidth());

        return realmProfileImage;
    }

    @Override
    public ProfileImageDataModel mapFromRealmModel(ProfileImageRealmModel profileImageRealmModel) {
        ProfileImageDataModel profileImageDataModel = new ProfileImageDataModel();
        profileImageDataModel.setAspectRatio(profileImageRealmModel.getAspectRatio());
        profileImageDataModel.setFilePath(profileImageRealmModel.getFilePath());
        profileImageDataModel.setHeight(profileImageRealmModel.getHeight());
        profileImageDataModel.setVoteAverage(profileImageRealmModel.getVoteAverage());
        profileImageDataModel.setVoteCount(profileImageRealmModel.getVoteCount());
        profileImageDataModel.setWidth(profileImageRealmModel.getWidth());

        return profileImageDataModel;
    }
}
