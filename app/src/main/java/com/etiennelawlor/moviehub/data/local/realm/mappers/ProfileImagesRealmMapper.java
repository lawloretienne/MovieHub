package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmProfileImage;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmProfileImages;
import com.etiennelawlor.moviehub.data.remote.response.ProfileImage;
import com.etiennelawlor.moviehub.data.remote.response.ProfileImages;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class ProfileImagesRealmMapper implements RealmMapper<ProfileImages, RealmProfileImages> {

    private ProfileImageRealmMapper profileImageRealmMapper = new ProfileImageRealmMapper();

    @Override
    public RealmProfileImages mapToRealmObject(ProfileImages profileImages) {
        RealmProfileImages realmProfileImages = Realm.getDefaultInstance().createObject(RealmProfileImages.class);

        if(profileImages != null){
            List<ProfileImage> profileImages1 = profileImages.getProfiles();
            RealmList<RealmProfileImage> realmProfileImages1 = new RealmList<>();
            if(profileImages1 != null && profileImages1.size()>0) {
                for (ProfileImage profileImage : profileImages1) {
                    realmProfileImages1.add(profileImageRealmMapper.mapToRealmObject(profileImage));
                }
            }
            realmProfileImages.setProfiles(realmProfileImages1);
        }

        return realmProfileImages;
    }

    @Override
    public ProfileImages mapFromRealmObject(RealmProfileImages realmProfileImages) {
        ProfileImages profileImages = new ProfileImages();

        RealmList<RealmProfileImage> realmProfileImages1 = realmProfileImages.getProfiles();
        List<ProfileImage> profileImages1 = new ArrayList<>();
        for(RealmProfileImage realmProfileImage : realmProfileImages1){
            profileImages1.add(profileImageRealmMapper.mapFromRealmObject(realmProfileImage));
        }
        profileImages.setProfiles(profileImages1);

        return profileImages;
    }
}
