package com.etiennelawlor.moviehub.data.local.realm;

import com.etiennelawlor.moviehub.data.local.realm.models.RealmMovie;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmMoviesPage;
import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.remote.response.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by etiennelawlor on 11/22/15.
 */
public class RealmUtility {

//    https://github.com/Innovatube/android-tdd-approach/blob/04c09ca0048c507e9492ff646b23b58e801dc9c0/app/src/main/java/com/example/androidtdd/data/model/Address.java

    private static final RealmMovieMapper realmMovieMapper = new RealmMovieMapper();
    private static final MovieMapper movieMapper = new MovieMapper();

    public static MoviesPage getMoviesPage(int pageNumber){
        MoviesPage moviesPage = new MoviesPage();

        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmMoviesPage> realmResults
                    = realm.where(RealmMoviesPage.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                if(realmResults.size() == pageNumber-1)
                    return null;

                RealmMoviesPage realmMoviesPage = realmResults.get(pageNumber-1);

                RealmList<RealmMovie> realmMovies = realmMoviesPage.getMovies();

                List<Movie> movies = new ArrayList<>();
                for(RealmMovie realmMovie : realmMovies){
                    movies.add(realmMovieMapper.map(realmMovie));
                }

                moviesPage.setMovies(movies);
                moviesPage.setPageNumber(realmMoviesPage.getPageNumber());
                moviesPage.setLastPage(realmMoviesPage.isLastPage());
                moviesPage.setExpiredAt(realmMoviesPage.getExpiredAt());

                return moviesPage;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void saveMoviesPage(MoviesPage moviesPage){
        Realm realm = Realm.getDefaultInstance();
        try {
            List<Movie> movies = moviesPage.getMovies();
            int pageNumber = moviesPage.getPageNumber();
            boolean isLastPage = moviesPage.isLastPage();
            Date expiredAt = moviesPage.getExpiredAt();

            realm.beginTransaction();

            RealmMoviesPage realmMoviesPage =
                    realm.createObject(RealmMoviesPage.class);

            RealmList<RealmMovie> realmMovies = new RealmList<>();
            for(Movie movie : movies){
                realmMovies.add(movieMapper.map(movie));
            }

            realmMoviesPage.setMovies(realmMovies);
            realmMoviesPage.setPageNumber(pageNumber);
            realmMoviesPage.setLastPage(isLastPage);
            realmMoviesPage.setExpiredAt(expiredAt);

            realm.copyToRealm(realmMoviesPage);
            realm.commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }
}
