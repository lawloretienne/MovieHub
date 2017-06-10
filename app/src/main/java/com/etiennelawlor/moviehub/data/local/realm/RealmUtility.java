package com.etiennelawlor.moviehub.data.local.realm;

import com.etiennelawlor.moviehub.data.local.realm.mappers.MovieRealmMapper;
import com.etiennelawlor.moviehub.data.local.realm.mappers.TelevisionShowRealmMapper;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmMovie;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmMoviesPage;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmTelevisionShow;
import com.etiennelawlor.moviehub.data.local.realm.models.RealmTelevisionShowsPage;
import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.model.TelevisionShowsPage;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;

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

    private static final MovieRealmMapper movieRealmMapper = new MovieRealmMapper();
    private static final TelevisionShowRealmMapper televisionShowRealmMapper = new TelevisionShowRealmMapper();


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
                    movies.add(movieRealmMapper.mapFromRealmObject(realmMovie));
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

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmMoviesPage realmMoviesPage =
                            realm.createObject(RealmMoviesPage.class);

                    RealmList<RealmMovie> realmMovies = new RealmList<>();
                    for(Movie movie : movies){
                        realmMovies.add(movieRealmMapper.mapToRealmObject(movie));
                    }

                    realmMoviesPage.setMovies(realmMovies);
                    realmMoviesPage.setPageNumber(pageNumber);
                    realmMoviesPage.setLastPage(isLastPage);
                    realmMoviesPage.setExpiredAt(expiredAt);

                    realm.copyToRealm(realmMoviesPage);
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }

    public static TelevisionShowsPage getTelevisionShowsPage(int pageNumber){
        TelevisionShowsPage televisionShowsPage = new TelevisionShowsPage();

        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmTelevisionShowsPage> realmResults
                    = realm.where(RealmTelevisionShowsPage.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                if(realmResults.size() == pageNumber-1)
                    return null;

                RealmTelevisionShowsPage realmTelevisionShowsPage = realmResults.get(pageNumber-1);

                RealmList<RealmTelevisionShow> realmTelevisionShows = realmTelevisionShowsPage.getTelevisionShows();

                List<TelevisionShow> televisionShows = new ArrayList<>();
                for(RealmTelevisionShow realmTelevisionShow : realmTelevisionShows){
                    televisionShows.add(televisionShowRealmMapper.mapFromRealmObject(realmTelevisionShow));
                }

                televisionShowsPage.setTelevisionShows(televisionShows);
                televisionShowsPage.setPageNumber(realmTelevisionShowsPage.getPageNumber());
                televisionShowsPage.setLastPage(realmTelevisionShowsPage.isLastPage());
                televisionShowsPage.setExpiredAt(realmTelevisionShowsPage.getExpiredAt());

                return televisionShowsPage;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void saveTelevisionShowsPage(TelevisionShowsPage televisionShowsPage){
        Realm realm = Realm.getDefaultInstance();
        try {
            List<TelevisionShow> televisionShows = televisionShowsPage.getTelevisionShows();
            int pageNumber = televisionShowsPage.getPageNumber();
            boolean isLastPage = televisionShowsPage.isLastPage();
            Date expiredAt = televisionShowsPage.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmTelevisionShowsPage realmTelevisionShowsPage =
                            realm.createObject(RealmTelevisionShowsPage.class);

                    RealmList<RealmTelevisionShow> realmTelevisionShows = new RealmList<>();
                    for(TelevisionShow televisionShow : televisionShows){
                        realmTelevisionShows.add(televisionShowRealmMapper.mapToRealmObject(televisionShow));
                    }

                    realmTelevisionShowsPage.setTelevisionShows(realmTelevisionShows);
                    realmTelevisionShowsPage.setPageNumber(pageNumber);
                    realmTelevisionShowsPage.setLastPage(isLastPage);
                    realmTelevisionShowsPage.setExpiredAt(expiredAt);

                    realm.copyToRealm(realmTelevisionShowsPage);
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }
}
