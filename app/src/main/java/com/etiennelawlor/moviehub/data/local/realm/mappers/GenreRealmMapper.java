package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmGenre;
import com.etiennelawlor.moviehub.data.remote.response.Genre;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class GenreRealmMapper implements RealmMapper<Genre, RealmGenre> {

    @Override
    public RealmGenre mapToRealmObject(Genre genre) {
        RealmGenre realmGenre = Realm.getDefaultInstance().createObject(RealmGenre.class);

        realmGenre.setId(genre.getId());
        realmGenre.setName(genre.getName());

        return realmGenre;
    }

    @Override
    public Genre mapFromRealmObject(RealmGenre realmGenre) {
        Genre genre = new Genre();
        genre.setId(realmGenre.getId());
        genre.setName(realmGenre.getName());

        return genre;
    }
}
