package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.ProfileImageRealmModel;
import com.etiennelawlor.moviehub.data.database.models.ProfileImagesRealmModel;
import com.etiennelawlor.moviehub.data.network.response.ProfileImage;
import com.etiennelawlor.moviehub.data.network.response.ProfileImages;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImagesRealmModelMapper implements RealmModelMapper<ProfileImages, ProfileImagesRealmModel> {

    private ProfileImageRealmModelMapper profileImageRealmMapper = new ProfileImageRealmModelMapper();

    @Override
    public ProfileImagesRealmModel mapToRealmModel(ProfileImages profileImages) {
        ProfileImagesRealmModel realmProfileImages = Realm.getDefaultInstance().createObject(ProfileImagesRealmModel.class);

        if(profileImages != null){
            List<ProfileImage> profileImages1 = profileImages.getProfiles();
            RealmList<ProfileImageRealmModel> realmProfileImages1 = new RealmList<>();
            if(profileImages1 != null && profileImages1.size()>0) {
                for (ProfileImage profileImage : profileImages1) {
                    realmProfileImages1.add(profileImageRealmMapper.mapToRealmModel(profileImage));
                }
            }
            realmProfileImages.setProfiles(realmProfileImages1);
        }

        return realmProfileImages;
    }

    @Override
    public ProfileImages mapFromRealmModel(ProfileImagesRealmModel profileImagesRealmModel) {
        ProfileImages profileImages = new ProfileImages();

        RealmList<ProfileImageRealmModel> realmProfileImages1 = profileImagesRealmModel.getProfiles();
        List<ProfileImage> profileImages1 = new ArrayList<>();
        for(ProfileImageRealmModel realmProfileImage : realmProfileImages1){
            profileImages1.add(profileImageRealmMapper.mapFromRealmModel(realmProfileImage));
        }
        profileImages.setProfiles(profileImages1);

        return profileImages;
    }
}
