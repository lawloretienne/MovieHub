package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.GenreDataModel;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class GenreRealmModelMapper implements RealmModelMapper<GenreDataModel, GenreRealmModel> {

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
}
