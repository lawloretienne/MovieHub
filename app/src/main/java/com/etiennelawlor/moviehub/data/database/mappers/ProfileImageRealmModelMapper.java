package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.ProfileImageRealmModel;
import com.etiennelawlor.moviehub.data.network.response.ProfileImageResponse;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImageRealmModelMapper implements RealmModelMapper<ProfileImageResponse, ProfileImageRealmModel> {

    @Override
    public ProfileImageRealmModel mapToRealmModel(ProfileImageResponse profileImage) {
        ProfileImageRealmModel realmProfileImage = Realm.getDefaultInstance().createObject(ProfileImageRealmModel.class);

        realmProfileImage.setAspectRatio(profileImage.getAspectRatio());
        realmProfileImage.setFilePath(profileImage.getFilePath());
        realmProfileImage.setHeight(profileImage.getHeight());
        realmProfileImage.setVoteAverage(profileImage.getVoteAverage());
        realmProfileImage.setVoteCount(profileImage.getVoteCount());
        realmProfileImage.setWidth(profileImage.getWidth());

        return realmProfileImage;
    }

    @Override
    public ProfileImageResponse mapFromRealmModel(ProfileImageRealmModel profileImageRealmModel) {
        ProfileImageResponse profileImage = new ProfileImageResponse();
        profileImage.setAspectRatio(profileImageRealmModel.getAspectRatio());
        profileImage.setFilePath(profileImageRealmModel.getFilePath());
        profileImage.setHeight(profileImageRealmModel.getHeight());
        profileImage.setVoteAverage(profileImageRealmModel.getVoteAverage());
        profileImage.setVoteCount(profileImageRealmModel.getVoteCount());
        profileImage.setWidth(profileImageRealmModel.getWidth());

        return profileImage;
    }
}
