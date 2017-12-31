package com.etiennelawlor.moviehub.data.database.mappers;

import com.etiennelawlor.moviehub.data.database.models.GenreRealmModel;
import com.etiennelawlor.moviehub.data.database.models.MovieRealmModel;
import com.etiennelawlor.moviehub.data.network.response.Genre;
import com.etiennelawlor.moviehub.data.network.response.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public class MovieRealmModelMapper implements RealmModelMapper<Movie, MovieRealmModel> {

    private GenreRealmModelMapper genreRealmMapper = new GenreRealmModelMapper();

    @Override
    public MovieRealmModel mapToRealmModel(Movie movie) {
        MovieRealmModel realmMovie = Realm.getDefaultInstance().createObject(MovieRealmModel.class);

        realmMovie.setAdult(movie.isAdult());
        realmMovie.setBackdropPath(movie.getBackdropPath());
        realmMovie.setBudget(movie.getBudget());

        List<Genre> genres = movie.getGenres();
        RealmList<GenreRealmModel> realmGenres = new RealmList<>();
        if(genres != null && genres.size()>0) {
            for (Genre genre : genres) {
                realmGenres.add(genreRealmMapper.mapToRealmModel(genre));
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
    public Movie mapFromRealmModel(MovieRealmModel movieRealmModel) {
        Movie movie = new Movie();
        movie.setAdult(movieRealmModel.isAdult());
        movie.setBackdropPath(movieRealmModel.getBackdropPath());
        movie.setBudget(movieRealmModel.getBudget());

        RealmList<GenreRealmModel> realmGenres = movieRealmModel.getGenres();
        List<Genre> genres = new ArrayList<>();
        for(GenreRealmModel realmGenre : realmGenres){
            genres.add(genreRealmMapper.mapFromRealmModel(realmGenre));
        }
        movie.setGenres(genres);
        movie.setHomepage(movieRealmModel.getHomepage());
        movie.setId(movieRealmModel.getId());
        movie.setImdbId(movieRealmModel.getImdbId());
        movie.setOriginalLanguage(movieRealmModel.getOriginalLanguage());
        movie.setOriginalTitle(movieRealmModel.getOriginalTitle());
        movie.setOverview(movieRealmModel.getOverview());
        movie.setPopularity(movieRealmModel.getPopularity());
//        movie.setPosterPalette(realmMovie.getPosterPalette());
        movie.setPosterPath(movieRealmModel.getPosterPath());
        movie.setReleaseDate(movieRealmModel.getReleaseDate());
        movie.setRevenue(movieRealmModel.getRevenue());
        movie.setRuntime(movieRealmModel.getRuntime());
        movie.setStatus(movieRealmModel.getStatus());
        movie.setTagline(movieRealmModel.getTagline());
        movie.setTitle(movieRealmModel.getTitle());
        movie.setVideo(movieRealmModel.isVideo());
        movie.setVoteAverage(movieRealmModel.getVoteAverage());
        movie.setVoteCount(movieRealmModel.getVoteCount());

        return movie;
    }
}
