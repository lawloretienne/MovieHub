package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.network.response.GenreResponse;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class GenreRealmModelMapper implements RealmModelMapper<GenreResponse, GenreRealmModel> {

    @Override
    public GenreRealmModel mapToRealmModel(GenreResponse genre) {
        GenreRealmModel realmGenre = Realm.getDefaultInstance().createObject(GenreRealmModel.class);

        realmGenre.setId(genre.getId());
        realmGenre.setName(genre.getName());

        return realmGenre;
    }

    @Override
    public GenreResponse mapFromRealmModel(GenreRealmModel genreRealmModel) {
        GenreResponse genre = new GenreResponse();
        genre.setId(genreRealmModel.getId());
        genre.setName(genreRealmModel.getName());

        return genre;
    }
}
