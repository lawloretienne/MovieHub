package com.etiennelawlor.moviehub.data.local.realm;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmGenre;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmMovie;
import com.etiennelawlor.moviehub.data.remote.response.Genre;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class GenreMapper implements Mapper<Genre, RealmGenre> {

    @Override
    public RealmGenre map(Genre genre) {

        RealmGenre realmGenre = Realm.getDefaultInstance().createObject(RealmGenre.class);

        realmGenre.setId(genre.getId());
        realmGenre.setName(genre.getName());

        return realmGenre;
    }
}
