package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.network.response.Genre;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class GenreRealmModelMapper implements RealmModelMapper<Genre, GenreRealmModel> {

    @Override
    public GenreRealmModel mapToRealmModel(Genre genre) {
        GenreRealmModel realmGenre = Realm.getDefaultInstance().createObject(GenreRealmModel.class);

        realmGenre.setId(genre.getId());
        realmGenre.setName(genre.getName());

        return realmGenre;
    }

    @Override
    public Genre mapFromRealmModel(GenreRealmModel genreRealmModel) {
        Genre genre = new Genre();
        genre.setId(genreRealmModel.getId());
        genre.setName(genreRealmModel.getName());

        return genre;
    }
}
