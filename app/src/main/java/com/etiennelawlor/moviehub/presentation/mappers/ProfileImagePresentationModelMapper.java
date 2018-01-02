package com.etiennelawlor.moviehub.presentation.mappers;

import com.etiennelawlor.moviehub.domain.models.ProfileImageDomainModel;
import com.etiennelawlor.moviehub.presentation.models.ProfileImagePresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 12/31/17.
 */

public class ProfileImagePresentationModelMapper implements PresentationModelMapper<ProfileImageDomainModel, ProfileImagePresentationModel>, PresentationModelListMapper<ProfileImageDomainModel, ProfileImagePresentationModel> {

    @Override
    public ProfileImagePresentationModel mapToPresentationModel(ProfileImageDomainModel profileImageDomainModel) {
        ProfileImagePresentationModel profileImagePresentationModel = new ProfileImagePresentationModel();
        profileImagePresentationModel.setAspectRatio(profileImageDomainModel.getAspectRatio());
        profileImagePresentationModel.setFilePath(profileImageDomainModel.getFilePath());
        profileImagePresentationModel.setHeight(profileImageDomainModel.getHeight());
        profileImagePresentationModel.setVoteAverage(profileImageDomainModel.getVoteAverage());
        profileImagePresentationModel.setVoteCount(profileImageDomainModel.getVoteCount());
        profileImagePresentationModel.setWidth(profileImageDomainModel.getWidth());
        return profileImagePresentationModel;
    }

    @Override
    public List<ProfileImagePresentationModel> mapListToPresentationModelList(List<ProfileImageDomainModel> profileImageDomainModels) {
        List<ProfileImagePresentationModel> profileImagePresentationModels = new ArrayList<>();
        if(profileImageDomainModels != null && profileImageDomainModels.size()>0) {
            for (ProfileImageDomainModel profileImageDomainModel : profileImageDomainModels) {
                profileImagePresentationModels.add(mapToPresentationModel(profileImageDomainModel));
            }
        }
        return profileImagePresentationModels;
    }
}
