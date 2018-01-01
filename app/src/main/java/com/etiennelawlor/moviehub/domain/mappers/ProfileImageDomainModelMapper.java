package com.etiennelawlor.moviehub.domain.mappers;

import com.etiennelawlor.moviehub.data.network.response.ProfileImageResponse;
import com.etiennelawlor.moviehub.data.repositories.models.ProfileImageDataModel;
import com.etiennelawlor.moviehub.domain.models.ProfileImageDomainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImageDomainModelMapper implements DomainModelMapper<ProfileImageDataModel, ProfileImageDomainModel>, DomainModelListMapper<ProfileImageDataModel, ProfileImageDomainModel> {

    @Override
    public ProfileImageDomainModel mapToDomainModel(ProfileImageDataModel profileImageDataModel) {
        ProfileImageDomainModel profileImageDomainModel = new ProfileImageDomainModel();
        profileImageDomainModel.setAspectRatio(profileImageDataModel.getAspectRatio());
        profileImageDomainModel.setFilePath(profileImageDataModel.getFilePath());
        profileImageDomainModel.setHeight(profileImageDataModel.getHeight());
        profileImageDomainModel.setVoteAverage(profileImageDataModel.getVoteAverage());
        profileImageDomainModel.setVoteCount(profileImageDataModel.getVoteCount());
        profileImageDomainModel.setWidth(profileImageDataModel.getWidth());
        return profileImageDomainModel;
    }

    @Override
    public List<ProfileImageDomainModel> mapListToDomainModelList(List<ProfileImageDataModel> profileImageDataModels) {
        List<ProfileImageDomainModel> profileImageDomainModels = new ArrayList<>();
        if(profileImageDataModels != null && profileImageDataModels.size()>0) {
            for (ProfileImageDataModel profileImageDataModel : profileImageDataModels) {
                profileImageDomainModels.add(mapToDomainModel(profileImageDataModel));
            }
        }
        return profileImageDomainModels;
    }
}
