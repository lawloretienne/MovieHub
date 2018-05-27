package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.GenreDataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class GenreRealmModelMapper implements RealmModelMapper<GenreDataModel, GenreRealmModel>, RealmModelListMapper<GenreDataModel, GenreRealmModel> {

    @Override
    public GenreRealmModel mapToRealmModel(GenreDataModel genreDataModel) {
        GenreRealmModel realmGenre = Realm.getDefaultInstance().createObject(GenreRealmModel.class);

        realmGenre.setId(genreDataModel.getId());
        realmGenre.setName(genreDataModel.getName());

        return realmGenre;
    }

    @Override
    public GenreDataModel mapFromRealmModel(GenreRealmModel genreRealmModel) {
        GenreDataModel genreDataModel = new GenreDataModel();
        genreDataModel.setId(genreRealmModel.getId());
        genreDataModel.setName(genreRealmModel.getName());

        return genreDataModel;
    }

    @Override
    public RealmList<GenreRealmModel> mapListToRealmModelList(List<GenreDataModel> genreDataModels) {
        RealmList<GenreRealmModel> realmGenres = new RealmList<>();
        if(genreDataModels != null && genreDataModels.size()>0) {
            for (GenreDataModel genreDataModel : genreDataModels) {
                realmGenres.add(mapToRealmModel(genreDataModel));
            }
        }
        return realmGenres;
    }

    @Override
    public List<GenreDataModel> mapListFromRealmModelList(RealmList<GenreRealmModel> genreRealmModels) {
        List<GenreDataModel> genreDataModels = new ArrayList<>();
        for(GenreRealmModel genreRealmModel : genreRealmModels){
            genreDataModels.add(mapFromRealmModel(genreRealmModel));
        }
        return genreDataModels;
    }
}
