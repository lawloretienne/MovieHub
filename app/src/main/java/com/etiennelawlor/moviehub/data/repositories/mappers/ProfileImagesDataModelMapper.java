package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ProfileImageResponse;
import com.etiennelawlor.moviehub.data.network.response.ProfileImagesResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImageDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImagesDataModel;

import java.util.ArrayList;
import java.util.List;

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
            List<ProfileImageResponse> profileImageResponses = profileImagesResponse.getProfiles();
            List<ProfileImageDataModel> profileImageDataModels = new ArrayList<>();
            if(profileImageResponses != null && profileImageResponses.size()>0) {
                for (ProfileImageResponse profileImageResponse : profileImageResponses) {
                    profileImageDataModels.add(profileImageDataModelMapper.mapToDataModel(profileImageResponse));
                }
            }
            profileImagesDataModel.setProfiles(profileImageDataModels);
        }

        return profileImagesDataModel;
    }
}
