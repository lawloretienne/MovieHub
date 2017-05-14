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

public class MovieMapper implements Mapper<Movie, RealmMovie> {

    private GenreMapper genreMapper = new GenreMapper();

    @Override
    public RealmMovie map(Movie movie) {

        RealmMovie realmMovie = Realm.getDefaultInstance().createObject(RealmMovie.class);

        realmMovie.setAdult(movie.isAdult());
        realmMovie.setBackdropPath(movie.getBackdropPath());
        realmMovie.setBudget(movie.getBudget());
        List<Genre> genres = movie.getGenres();

        RealmList<RealmGenre> realmGenres = new RealmList<>();
        if(genres != null && genres.size()>0) {
            for (Genre genre : genres) {
                realmGenres.add(genreMapper.map(genre));
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
}
