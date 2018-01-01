package com.etiennelawlor.moviehub.data.repositories.mappers;

import com.etiennelawlor.moviehub.data.network.response.ProfileImageResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImageDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImageDataModelMapper implements DataModelMapper<ProfileImageResponse, ProfileImageDataModel>, DataModelListMapper<ProfileImageResponse, ProfileImageDataModel> {

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

    @Override
    public List<ProfileImageDataModel> mapListToDataModelList(List<ProfileImageResponse> profileImageResponses) {
        List<ProfileImageDataModel> profileImageDataModels = new ArrayList<>();
        if(profileImageResponses != null && profileImageResponses.size()>0) {
            for (ProfileImageResponse profileImageResponse : profileImageResponses) {
                profileImageDataModels.add(mapToDataModel(profileImageResponse));
            }
        }
        return profileImageDataModels;
    }
}
