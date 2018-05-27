package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.repositories.models.ProfileImagesDataModel;
import com.etiennelawlor.moviehub.domain.models.ProfileImagesDomainModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesDomainModelMapper implements DomainModelMapper<ProfileImagesDataModel, ProfileImagesDomainModel> {

    // region Member Variables
    private ProfileImageDomainModelMapper profileImageDomainModelMapper = new ProfileImageDomainModelMapper();
    // endregion

    @Override
    public ProfileImagesDomainModel mapToDomainModel(ProfileImagesDataModel profileImagesDataModel) {
        ProfileImagesDomainModel profileImagesDomainModel = new ProfileImagesDomainModel();
        if(profileImagesDataModel != null){
            profileImagesDomainModel.setProfiles(profileImageDomainModelMapper.mapListToDomainModelList(profileImagesDataModel.getProfiles()));
        }
        return profileImagesDomainModel;
    }
}
