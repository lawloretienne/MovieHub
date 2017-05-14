package com.etiennelawlor.moviehub.data.local.realm;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmGenre;
import com.etiennelawlor.moviehub.data.remote.response.Genre;

import io.realm.Realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class RealmGenreMapper implements Mapper<RealmGenre, Genre> {

    @Override
    public Genre map(RealmGenre realmGenre) {

        Genre genre = new Genre();
        genre.setId(realmGenre.getId());
        genre.setName(realmGenre.getName());

        return genre;
    }
}
