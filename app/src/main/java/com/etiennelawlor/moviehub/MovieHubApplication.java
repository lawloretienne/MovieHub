package com.etiennelawlor.moviehub;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.etiennelawlor.moviehub.di.component.ApplicationComponent;
import com.etiennelawlor.moviehub.di.component.DaggerApplicationComponent;
import com.etiennelawlor.moviehub.di.module.AndroidModule;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by etiennelawlor on 1/20/16.
 */
public class MovieHubApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    // region Static Variables
    private static MovieHubApplication currentApplication = null;
    // endregion

    // region Member Variables
    private RefWatcher refWatcher;
    private ApplicationComponent applicationComponent;
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate() {
        super.onCreate();

        initializeTimber();
        initializeLeakCanary();
        initializeRealm();

        currentApplication = this;

        applicationComponent = createApplicationComponent();
    }
    // endregion

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    // region Helper Methods

    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .androidModule(new AndroidModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MovieHubApplication getInstance() {
        return currentApplication;
    }

    public static RefWatcher getRefWatcher(Context context) {
        MovieHubApplication application = (MovieHubApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private void initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();

        // Deletes the realm
        // Use when you want a clean slate for dev
//        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                // Add the line number to the tag
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    // endregion

    // region Inner Classes

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class ReleaseTree extends Timber.Tree {

        private static final int MAX_LOG_LENGTH = 4000;

        @Override
        protected boolean isLoggable(String tag, int priority) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
                return false;
            }

            // Only log WARN, ERROR, WTF
            return true;
        }

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (isLoggable(priority)) {

//            FakeCrashLibrary.log(priority, tag, message);

//            if (t != null) {
//                if (priority == Log.ERROR) {
//                    FakeCrashLibrary.logError(t);
//                } else if (priority == Log.WARN) {
//                    FakeCrashLibrary.logWarning(t);
//                }
//            }

                // Message is short enough, does not need to be broken into chunks
                if (message.length() < MAX_LOG_LENGTH) {
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, message);
                    } else {
                        Log.println(priority, tag, message);
                    }
                    return;
                }

                // Split by line, then ensure each line can fit into Log's maximum length
                for (int i = 0, length = message.length(); i < length; i++) {
                    int newline = message.indexOf('\n', i);
                    newline = newline != -1 ? newline : length;
                    do {
                        int end = Math.min(newline, i + MAX_LOG_LENGTH);
                        String part = message.substring(i, end);
                        if (priority == Log.ASSERT) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }
                        i = end;
                    } while (i < newline);
                }
            }
        }
    }

    // endregion
}
