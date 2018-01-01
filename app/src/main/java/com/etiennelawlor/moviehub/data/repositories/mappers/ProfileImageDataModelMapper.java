package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ProfileImageResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImageDataModel;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImageDataModelMapper implements DataModelMapper<ProfileImageResponse, ProfileImageDataModel> {

    @Override
    public ProfileImageDataModel mapToDataModel(ProfileImageResponse profileImageResponse) {
        ProfileImageDataModel profileImageDataModel = new ProfileImageDataModel();
        profileImageDataModel.setAspectRatio(profileImageResponse.getAspectRatio());
        profileImageDataModel.setFilePath(profileImageResponse.getFilePath());
        profileImageDataModel.setHeight(profileImageResponse.getHeight());
        profileImageDataModel.setVoteAverage(profileImageResponse.getVoteAverage());
        profileImageDataModel.setVoteCount(profileImageResponse.getVoteCount());
        profileImageDataModel.setWidth(profileImageResponse.getWidth());

        return profileImageDataModel;
    }
}
