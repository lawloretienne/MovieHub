package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.ProfileImagesRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImagesDataModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImagesRealmModelMapper implements RealmModelMapper<ProfileImagesDataModel, ProfileImagesRealmModel> {

    private ProfileImageRealmModelMapper profileImageRealmMapper = new ProfileImageRealmModelMapper();

    @Override
    public ProfileImagesRealmModel mapToRealmModel(ProfileImagesDataModel profileImagesDataModel) {
        ProfileImagesRealmModel realmProfileImages = Realm.getDefaultInstance().createObject(ProfileImagesRealmModel.class);
        if(profileImagesDataModel != null){
            realmProfileImages.setProfiles(profileImageRealmMapper.mapListToRealmModelList(profileImagesDataModel.getProfiles()));
        }
        return realmProfileImages;
    }

    @Override
    public ProfileImagesDataModel mapFromRealmModel(ProfileImagesRealmModel profileImagesRealmModel) {
        ProfileImagesDataModel profileImagesDataModel = new ProfileImagesDataModel();
        profileImagesDataModel.setProfiles(profileImageRealmMapper.mapListFromRealmModelList(profileImagesRealmModel.getProfiles()));
        return profileImagesDataModel;
    }
}
