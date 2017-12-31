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
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsDataModel;

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

    public static MoviesDataModel getMoviesDataModel(int pageNumber){
        MoviesDataModel moviesDataModel = new MoviesDataModel();

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

                moviesDataModel.setMovies(movies);
                moviesDataModel.setPageNumber(realmMoviesPage.getPageNumber());
                moviesDataModel.setLastPage(realmMoviesPage.isLastPage());
                moviesDataModel.setExpiredAt(realmMoviesPage.getExpiredAt());

                return moviesDataModel;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void saveMoviesDataModel(MoviesDataModel moviesDataModel){
        Realm realm = Realm.getDefaultInstance();
        try {
            List<Movie> movies = moviesDataModel.getMovies();
            int pageNumber = moviesDataModel.getPageNumber();
            boolean isLastPage = moviesDataModel.isLastPage();
            Date expiredAt = moviesDataModel.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmMoviesPage realmMoviesPage = new RealmMoviesPage();

                    RealmList<RealmMovie> realmMovies = new RealmList<>();
                    for(Movie movie : movies){
                        realmMovies.add(movieRealmMapper.mapToRealmObject(movie));
                    }

                    realmMoviesPage.setMovies(realmMovies);
                    realmMoviesPage.setPageNumber(pageNumber);
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

    public static TelevisionShowsDataModel getTelevisionShowsDataModel(int pageNumber){
        TelevisionShowsDataModel televisionShowsDataModel = new TelevisionShowsDataModel();

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

                televisionShowsDataModel.setTelevisionShows(televisionShows);
                televisionShowsDataModel.setPageNumber(realmTelevisionShowsPage.getPageNumber());
                televisionShowsDataModel.setLastPage(realmTelevisionShowsPage.isLastPage());
                televisionShowsDataModel.setExpiredAt(realmTelevisionShowsPage.getExpiredAt());

                return televisionShowsDataModel;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void saveTelevisionShowsDataModel(TelevisionShowsDataModel televisionShowsDataModel){
        Realm realm = Realm.getDefaultInstance();
        try {
            List<TelevisionShow> televisionShows = televisionShowsDataModel.getTelevisionShows();
            int pageNumber = televisionShowsDataModel.getPageNumber();
            boolean isLastPage = televisionShowsDataModel.isLastPage();
            Date expiredAt = televisionShowsDataModel.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmTelevisionShowsPage realmTelevisionShowsPage = new RealmTelevisionShowsPage();

                    RealmList<RealmTelevisionShow> realmTelevisionShows = new RealmList<>();
                    for(TelevisionShow televisionShow : televisionShows){
                        realmTelevisionShows.add(televisionShowRealmMapper.mapToRealmObject(televisionShow));
                    }

                    realmTelevisionShowsPage.setTelevisionShows(realmTelevisionShows);
                    realmTelevisionShowsPage.setPageNumber(pageNumber);
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

    public static PersonsDataModel getPersonsDataModel(int pageNumber){
        PersonsDataModel personsDataModel = new PersonsDataModel();

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

                personsDataModel.setPersons(persons);
                personsDataModel.setPageNumber(realmPersonsPage.getPageNumber());
                personsDataModel.setLastPage(realmPersonsPage.isLastPage());
                personsDataModel.setExpiredAt(realmPersonsPage.getExpiredAt());

                return personsDataModel;
            } else {
                return null;
            }
        } finally {
            realm.close();
        }
    }

    public static void savePersonsDataModel(PersonsDataModel personsDataModel){
        Realm realm = Realm.getDefaultInstance();
        try {
            List<Person> persons = personsDataModel.getPersons();
            int pageNumber = personsDataModel.getPageNumber();
            boolean isLastPage = personsDataModel.isLastPage();
            Date expiredAt = personsDataModel.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmPersonsPage realmPersonsPage = new RealmPersonsPage();

                    RealmList<RealmPerson> realmPersons = new RealmList<>();
                    for(Person person : persons){
                        realmPersons.add(personRealmMapper.mapToRealmObject(person));
                    }

                    realmPersonsPage.setPersons(realmPersons);
                    realmPersonsPage.setPageNumber(pageNumber);
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
