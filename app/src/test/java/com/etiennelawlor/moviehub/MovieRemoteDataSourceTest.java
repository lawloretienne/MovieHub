package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.data.network.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.network.MovieHubService;
import com.etiennelawlor.moviehub.data.network.ServiceGenerator;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.di.component.DaggerApplicationComponent;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.NetworkModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by etiennelawlor on 12/25/17.
 */

public class MovieRemoteDataSourceTest {

    private static final long MOVIE_ID = 181808;
    private static final int PAGE = 1;

    private MockWebServer server;
    private JsonReader jsonReader;
    private MovieHubService movieHubService;

//    @Inject
//    MovieHubService movieHubService;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        jsonReader = new JsonReader();

        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws
                    InterruptedException {
                String p = request.getPath();
                MockResponse response = new MockResponse().setResponseCode(200);
                if (p.equals("movie/popular")) {
                    return response.setBody(jsonReader.readString
                            ("json/popular_movies.json"));
                } else if (p.equals("movie/" + MOVIE_ID)) {
                    return response.setBody(jsonReader.readString
                            ("json/movie.json"));
                }

                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");

//        movieHubService = ServiceGenerator.createService(
//                MovieHubService.class,
//                MovieHubService.BASE_URL,
//                new AuthorizedNetworkInterceptor(context));

//        DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(MovieHubApplication.getInstance()))
//                .build()
//                .plus(new NetworkModule())
//                .inject(this);
    }

    @Test
    public void getPopularMovies() throws Exception {
        TestObserver<MoviesEnvelope> testObserver = new TestObserver<>();
        movieHubService.getPopularMovies(PAGE)
                .subscribeWith(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

//        MoviesEnvelope moviesEnvelope = (MoviesEnvelope) testObserver.getEvents().get(0).get(0);

//        assertEquals( , moviesEnvelope.getMovies());


//        testSubscriber.assertNoErrors();
//        testSubscriber.assertValueCount(1);
//
//        // correctness of fields
//        Recipient r = testSubscriber.getOnNextEvents().get(0).get(0);
//        assertEquals("FIOT", r.getSubdivisionName());
//        assertEquals("Teacher", r.getProfileName());

    }

    @Test
    public void getMovie() throws Exception {
        TestObserver<Movie> testObserver = new TestObserver<>();
        movieHubService.getMovie(MOVIE_ID)
                .subscribeWith(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

//        Movie movie = (Movie) testObserver.getEvents().get(0).get(0);

//        assertEquals( , moviesEnvelope.getMovies());

    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

}
