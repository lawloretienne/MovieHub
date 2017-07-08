package com.etiennelawlor.moviehub.data.database;

import com.etiennelawlor.moviehub.data.database.mappers.MovieRealmMapper;
import com.etiennelawlor.moviehub.data.database.mappers.PersonRealmMapper;
import com.etiennelawlor.moviehub.data.database.mappers.TelevisionShowRealmMapper;
import com.etiennelawlor.moviehub.data.database.models.RealmMovie;
import com.etiennelawlor.moviehub.data.database.models.RealmMoviesPage;
import com.etiennelawlor.moviehub.data.database.models.RealmPerson;
import com.etiennelawlor.moviehub.data.database.models.RealmPersonsPage;
import com.etiennelawlor.moviehub.data.database.models.RealmTelevisionShow;
import com.etiennelawlor.moviehub.data.database.models.RealmTelevisionShowsPage;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;

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
    private static final PersonRealmMapper personRealmMapper = new PersonRealmMapper();

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
                            realm.createObject(RealmMoviesPage.class, pageNumber);

                    RealmList<RealmMovie> realmMovies = new RealmList<>();
                    for(Movie movie : movies){
                        realmMovies.add(movieRealmMapper.mapToRealmObject(movie));
                    }

                    realmMoviesPage.setMovies(realmMovies);
                    realmMoviesPage.setLastPage(isLastPage);
                    realmMoviesPage.setExpiredAt(expiredAt);

                    realm.copyToRealmOrUpdate(realmMoviesPage);
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
                            realm.createObject(RealmTelevisionShowsPage.class, pageNumber);

                    RealmList<RealmTelevisionShow> realmTelevisionShows = new RealmList<>();
                    for(TelevisionShow televisionShow : televisionShows){
                        realmTelevisionShows.add(televisionShowRealmMapper.mapToRealmObject(televisionShow));
                    }

                    realmTelevisionShowsPage.setTelevisionShows(realmTelevisionShows);
                    realmTelevisionShowsPage.setLastPage(isLastPage);
                    realmTelevisionShowsPage.setExpiredAt(expiredAt);

                    realm.copyToRealmOrUpdate(realmTelevisionShowsPage);
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }

    public static PersonsPage getPersonsPage(int pageNumber){
        PersonsPage personsPage = new PersonsPage();

        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<RealmPersonsPage> realmResults
                    = realm.where(RealmPersonsPage.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                if(realmResults.size() == pageNumber-1)
                    return null;

                RealmPersonsPage realmPersonsPage = realmResults.get(pageNumber-1);

                RealmList<RealmPerson> realmPersons = realmPersonsPage.getPersons();

                List<Person> persons = new ArrayList<>();
                for(RealmPerson realmPerson : realmPersons){
                    persons.add(personRealmMapper.mapFromRealmObject(realmPerson));
                }

                personsPage.setPersons(persons);
                personsPage.setPageNumber(realmPersonsPage.getPageNumber());
                personsPage.setLastPage(realmPersonsPage.isLastPage());
                personsPage.setExpiredAt(realmPersonsPage.getExpiredAt());

                return personsPage;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void savePersonsPage(PersonsPage personsPage){
        Realm realm = Realm.getDefaultInstance();
        try {
            List<Person> persons = personsPage.getPersons();
            int pageNumber = personsPage.getPageNumber();
            boolean isLastPage = personsPage.isLastPage();
            Date expiredAt = personsPage.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmPersonsPage realmPersonsPage =
                            realm.createObject(RealmPersonsPage.class, pageNumber);

                    RealmList<RealmPerson> realmPersons = new RealmList<>();
                    for(Person person : persons){
                        realmPersons.add(personRealmMapper.mapToRealmObject(person));
                    }

                    realmPersonsPage.setPersons(realmPersons);
                    realmPersonsPage.setLastPage(isLastPage);
                    realmPersonsPage.setExpiredAt(expiredAt);

                    realm.copyToRealmOrUpdate(realmPersonsPage);
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }

}
