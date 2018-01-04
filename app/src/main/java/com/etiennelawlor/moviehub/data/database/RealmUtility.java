package com.etiennelawlor.moviehub.data.database;

import com.etiennelawlor.moviehub.data.database.mappers.MovieRealmModelMapper;
import com.etiennelawlor.moviehub.data.database.mappers.PersonRealmModelMapper;
import com.etiennelawlor.moviehub.data.database.mappers.TelevisionShowRealmModelMapper;
import com.etiennelawlor.moviehub.data.database.models.MoviesRealmModel;
import com.etiennelawlor.moviehub.data.database.models.PersonsRealmModel;
import com.etiennelawlor.moviehub.data.database.models.TelevisionShowsRealmModel;
import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.PersonsDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by etiennelawlor on 11/22/15.
 */
public class RealmUtility {

//    https://github.com/Innovatube/android-tdd-approach/blob/04c09ca0048c507e9492ff646b23b58e801dc9c0/app/src/main/java/com/example/androidtdd/data/model/Address.java

    private static final MovieRealmModelMapper movieRealmModelMapper = new MovieRealmModelMapper();
    private static final TelevisionShowRealmModelMapper televisionShowRealmModelMapper = new TelevisionShowRealmModelMapper();
    private static final PersonRealmModelMapper personRealmModelMapper = new PersonRealmModelMapper();

    public static MoviesDataModel getMoviesDataModel(int pageNumber){
        MoviesDataModel moviesDataModel = new MoviesDataModel();

        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<MoviesRealmModel> realmResults
                    = realm.where(MoviesRealmModel.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                if(realmResults.size() == pageNumber-1)
                    return null;

                MoviesRealmModel moviesRealmModel = realmResults.get(pageNumber-1);
                moviesDataModel.setMovies(movieRealmModelMapper.mapListFromRealmModelList(moviesRealmModel.getMovies()));
                moviesDataModel.setPageNumber(moviesRealmModel.getPageNumber());
                moviesDataModel.setLastPage(moviesRealmModel.isLastPage());
                moviesDataModel.setExpiredAt(moviesRealmModel.getExpiredAt());

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
            List<MovieDataModel> movies = moviesDataModel.getMovies();
            int pageNumber = moviesDataModel.getPageNumber();
            boolean isLastPage = moviesDataModel.isLastPage();
            Date expiredAt = moviesDataModel.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    MoviesRealmModel moviesRealmModel = new MoviesRealmModel();
                    moviesRealmModel.setMovies(movieRealmModelMapper.mapListToRealmModelList(movies));
                    moviesRealmModel.setPageNumber(pageNumber);
                    moviesRealmModel.setLastPage(isLastPage);
                    moviesRealmModel.setExpiredAt(expiredAt);

                    realm.copyToRealmOrUpdate(moviesRealmModel);
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
            RealmResults<TelevisionShowsRealmModel> realmResults
                    = realm.where(TelevisionShowsRealmModel.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                if(realmResults.size() == pageNumber-1)
                    return null;

                TelevisionShowsRealmModel televisionShowsRealmModel = realmResults.get(pageNumber-1);
                televisionShowsDataModel.setTelevisionShows(televisionShowRealmModelMapper.mapListFromRealmModelList(televisionShowsRealmModel.getTelevisionShows()));
                televisionShowsDataModel.setPageNumber(televisionShowsRealmModel.getPageNumber());
                televisionShowsDataModel.setLastPage(televisionShowsRealmModel.isLastPage());
                televisionShowsDataModel.setExpiredAt(televisionShowsRealmModel.getExpiredAt());

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
            List<TelevisionShowDataModel> televisionShowDataModels = televisionShowsDataModel.getTelevisionShows();
            int pageNumber = televisionShowsDataModel.getPageNumber();
            boolean isLastPage = televisionShowsDataModel.isLastPage();
            Date expiredAt = televisionShowsDataModel.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    TelevisionShowsRealmModel televisionShowsRealmModel = new TelevisionShowsRealmModel();
                    televisionShowsRealmModel.setTelevisionShows(televisionShowRealmModelMapper.mapListToRealmModelList(televisionShowDataModels));
                    televisionShowsRealmModel.setPageNumber(pageNumber);
                    televisionShowsRealmModel.setLastPage(isLastPage);
                    televisionShowsRealmModel.setExpiredAt(expiredAt);

                    realm.copyToRealmOrUpdate(televisionShowsRealmModel);
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
            RealmResults<PersonsRealmModel> realmResults
                    = realm.where(PersonsRealmModel.class).findAll();
            if(realmResults != null && realmResults.isValid() && realmResults.size() > 0){
                if(realmResults.size() == pageNumber-1)
                    return null;

                PersonsRealmModel personsRealmModel = realmResults.get(pageNumber-1);
                personsDataModel.setPersons(personRealmModelMapper.mapListFromRealmModelList(personsRealmModel.getPersons()));
                personsDataModel.setPageNumber(personsRealmModel.getPageNumber());
                personsDataModel.setLastPage(personsRealmModel.isLastPage());
                personsDataModel.setExpiredAt(personsRealmModel.getExpiredAt());

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
            List<PersonDataModel> persons = personsDataModel.getPersons();
            int pageNumber = personsDataModel.getPageNumber();
            boolean isLastPage = personsDataModel.isLastPage();
            Date expiredAt = personsDataModel.getExpiredAt();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    PersonsRealmModel personsRealmModel = new PersonsRealmModel();
                    personsRealmModel.setPersons(personRealmModelMapper.mapListToRealmModelList(persons));
                    personsRealmModel.setPageNumber(pageNumber);
                    personsRealmModel.setLastPage(isLastPage);
                    personsRealmModel.setExpiredAt(expiredAt);

                    realm.copyToRealmOrUpdate(personsRealmModel);
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            realm.close();
        }
    }

}
