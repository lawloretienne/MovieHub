package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ProfileImagesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImagesDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagesDataModelMapper implements DataModelMapper<ProfileImagesResponse, ProfileImagesDataModel> {

    // region Member Variables
    private ProfileImageDataModelMapper profileImageDataModelMapper = new ProfileImageDataModelMapper();
    // endregion

    @Override
    public ProfileImagesDataModel mapToDataModel(ProfileImagesResponse profileImagesResponse) {
        ProfileImagesDataModel profileImagesDataModel = new ProfileImagesDataModel();
        if(profileImagesResponse != null){
            profileImagesDataModel.setProfiles(profileImageDataModelMapper.mapListToDataModelList(profileImagesResponse.getProfiles()));
        }
        return profileImagesDataModel;
    }
}
