package com.etiennelawlor.moviehub.data.local.realm.mappers;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmGenre;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmMovie;
import com.etiennelawlor.moviehub.data.remote.response.Genre;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class MovieRealmMapper implements RealmMapper<Movie, RealmMovie> {

    private GenreRealmMapper genreRealmMapper = new GenreRealmMapper();

    @Override
    public RealmMovie mapToRealmObject(Movie movie) {
        RealmMovie realmMovie = Realm.getDefaultInstance().createObject(RealmMovie.class);

        realmMovie.setAdult(movie.isAdult());
        realmMovie.setBackdropPath(movie.getBackdropPath());
        realmMovie.setBudget(movie.getBudget());
        List<Genre> genres = movie.getGenres();

        RealmList<RealmGenre> realmGenres = new RealmList<>();
        if(genres != null && genres.size()>0) {
            for (Genre genre : genres) {
                realmGenres.add(genreRealmMapper.mapToRealmObject(genre));
            }
        }
        realmMovie.setGenres(realmGenres);

        realmMovie.setHomepage(movie.getHomepage());
        realmMovie.setId(movie.getId());
        realmMovie.setImdbId(movie.getImdbId());
        realmMovie.setOriginalLanguage(movie.getOriginalLanguage());
        realmMovie.setOriginalTitle(movie.getOriginalTitle());
        realmMovie.setOverview(movie.getOverview());
        realmMovie.setPopularity(movie.getPopularity());
//        realmMovie.setPosterPalette(movie.getPosterPalette());
        realmMovie.setPosterPath(movie.getPosterPath());
        realmMovie.setReleaseDate(movie.getReleaseDate());
        realmMovie.setRevenue(movie.getRevenue());
        realmMovie.setRuntime(movie.getRuntime());
        realmMovie.setStatus(movie.getStatus());
        realmMovie.setTagline(movie.getTagline());
        realmMovie.setTitle(movie.getTitle());
        realmMovie.setVideo(movie.isVideo());
        realmMovie.setVoteAverage(movie.getVoteAverage());
        realmMovie.setVoteCount(movie.getVoteCount());

        return realmMovie;
    }

    @Override
    public Movie mapFromRealmObject(RealmMovie realmMovie) {
        Movie movie = new Movie();
        movie.setAdult(realmMovie.isAdult());
        movie.setBackdropPath(realmMovie.getBackdropPath());
        movie.setBudget(realmMovie.getBudget());

        RealmList<RealmGenre> realmGenres = realmMovie.getGenres();
        List<Genre> genres = new ArrayList<>();
        for(RealmGenre realmGenre : realmGenres){
            genres.add(genreRealmMapper.mapFromRealmObject(realmGenre));
        }
        movie.setGenres(genres);
        movie.setHomepage(realmMovie.getHomepage());
        movie.setId(realmMovie.getId());
        movie.setImdbId(realmMovie.getImdbId());
        movie.setOriginalLanguage(realmMovie.getOriginalLanguage());
        movie.setOriginalTitle(realmMovie.getOriginalTitle());
        movie.setOverview(realmMovie.getOverview());
        movie.setPopularity(realmMovie.getPopularity());
//        movie.setPosterPalette(realmMovie.getPosterPalette());
        movie.setPosterPath(realmMovie.getPosterPath());
        movie.setReleaseDate(realmMovie.getReleaseDate());
        movie.setRevenue(realmMovie.getRevenue());
        movie.setRuntime(realmMovie.getRuntime());
        movie.setStatus(realmMovie.getStatus());
        movie.setTagline(realmMovie.getTagline());
        movie.setTitle(realmMovie.getTitle());
        movie.setVideo(realmMovie.isVideo());
        movie.setVoteAverage(realmMovie.getVoteAverage());
        movie.setVoteCount(realmMovie.getVoteCount());

        return movie;
    }
}
