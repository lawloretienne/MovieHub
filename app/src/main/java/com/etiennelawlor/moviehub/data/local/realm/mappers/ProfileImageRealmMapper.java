package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmProfileImage;
import com.etiennelawlor.moviehub.data.remote.response.ProfileImage;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImageRealmMapper implements RealmMapper<ProfileImage, RealmProfileImage> {

    @Override
    public RealmProfileImage mapToRealmObject(ProfileImage profileImage) {
        RealmProfileImage realmProfileImage = Realm.getDefaultInstance().createObject(RealmProfileImage.class);

        realmProfileImage.setAspectRatio(profileImage.getAspectRatio());
        realmProfileImage.setFilePath(profileImage.getFilePath());
        realmProfileImage.setHeight(profileImage.getHeight());
        realmProfileImage.setVoteAverage(profileImage.getVoteAverage());
        realmProfileImage.setVoteCount(profileImage.getVoteCount());
        realmProfileImage.setWidth(profileImage.getWidth());

        return realmProfileImage;
    }

    @Override
    public ProfileImage mapFromRealmObject(RealmProfileImage realmProfileImage) {
        ProfileImage profileImage = new ProfileImage();
        profileImage.setAspectRatio(realmProfileImage.getAspectRatio());
        profileImage.setFilePath(realmProfileImage.getFilePath());
        profileImage.setHeight(realmProfileImage.getHeight());
        profileImage.setVoteAverage(realmProfileImage.getVoteAverage());
        profileImage.setVoteCount(realmProfileImage.getVoteCount());
        profileImage.setWidth(realmProfileImage.getWidth());

        return profileImage;
    }
}
