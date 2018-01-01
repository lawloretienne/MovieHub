package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.ProfileImageRealmModel;
import com.etiennelawlor.moviehub.data.database.models.ProfileImagesRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImageDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImagesDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImagesRealmModelMapper implements RealmModelMapper<ProfileImagesDataModel, ProfileImagesRealmModel> {

    private ProfileImageRealmModelMapper profileImageRealmMapper = new ProfileImageRealmModelMapper();

    @Override
    public ProfileImagesRealmModel mapToRealmModel(ProfileImagesDataModel profileImagesDataModel) {
        ProfileImagesRealmModel realmProfileImages = Realm.getDefaultInstance().createObject(ProfileImagesRealmModel.class);

        if(profileImagesDataModel != null){
            List<ProfileImageDataModel> profileImageDataModels = profileImagesDataModel.getProfiles();
            RealmList<ProfileImageRealmModel> profileImageRealmModels = new RealmList<>();
            if(profileImageDataModels != null && profileImageDataModels.size()>0) {
                for (ProfileImageDataModel profileImageDataModel : profileImageDataModels) {
                    profileImageRealmModels.add(profileImageRealmMapper.mapToRealmModel(profileImageDataModel));
                }
            }
            realmProfileImages.setProfiles(profileImageRealmModels);
        }

        return realmProfileImages;
    }

    @Override
    public ProfileImagesDataModel mapFromRealmModel(ProfileImagesRealmModel profileImagesRealmModel) {
        ProfileImagesDataModel profileImagesDataModel = new ProfileImagesDataModel();

        RealmList<ProfileImageRealmModel> profileImageRealmModels = profileImagesRealmModel.getProfiles();
        List<ProfileImageDataModel> profileImageDataModels = new ArrayList<>();
        for(ProfileImageRealmModel profileImageRealmModel : profileImageRealmModels){
            profileImageDataModels.add(profileImageRealmMapper.mapFromRealmModel(profileImageRealmModel));
        }
        profileImagesDataModel.setProfiles(profileImageDataModels);

        return profileImagesDataModel;
    }
}
