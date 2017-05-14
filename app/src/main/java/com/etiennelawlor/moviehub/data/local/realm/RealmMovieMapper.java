package com.etiennelawlor.moviehub.data.local.realm;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmGenre;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmMovie;
import com.etiennelawlor.moviehub.data.remote.response.Genre;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class RealmMovieMapper implements Mapper<RealmMovie, Movie> {

    private RealmGenreMapper realmGenreMapper = new RealmGenreMapper();

    @Override
    public Movie map(RealmMovie realmMovie) {
        Movie movie = new Movie();
        movie.setAdult(realmMovie.isAdult());
        movie.setBackdropPath(realmMovie.getBackdropPath());
        movie.setBudget(realmMovie.getBudget());

        RealmList<RealmGenre> realmGenres = realmMovie.getGenres();
        List<Genre> genres = new ArrayList<>();
        for(RealmGenre realmGenre : realmGenres){
            genres.add(realmGenreMapper.map(realmGenre));
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
