package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.ProfileImagesDomainModel;
import com.etiennelawlor.moviehub.presentation.models.ProfileImagesPresentationModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesPresentationModelMapper implements PresentationModelMapper<ProfileImagesDomainModel, ProfileImagesPresentationModel> {

    // region Member Variables
    private ProfileImagePresentationModelMapper profileImagePresentationModelMapper = new ProfileImagePresentationModelMapper();
    // endregion

    @Override
    public ProfileImagesPresentationModel mapToPresentationModel(ProfileImagesDomainModel profileImagesDomainModel) {
        ProfileImagesPresentationModel profileImagesPresentationModel = new ProfileImagesPresentationModel();
        if(profileImagesDomainModel != null){
            profileImagesPresentationModel.setProfiles(profileImagePresentationModelMapper.mapListToPresentationModelList(profileImagesDomainModel.getProfiles()));
        }
        return profileImagesPresentationModel;
    }
}
