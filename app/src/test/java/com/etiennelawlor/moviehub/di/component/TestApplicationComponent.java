package com.etiennelawlor.moviehub.di.component;

import com.etiennelawlor.moviehub.MovieRemoteDataSourceTest;
import com.etiennelawlor.moviehub.di.module.TestNetworkModule;
import com.etiennelawlor.moviehub.di.module.AndroidModule;
import com.etiennelawlor.moviehub.di.module.ApplicationModule;
import com.etiennelawlor.moviehub.di.module.RxModule;
import com.etiennelawlor.moviehub.di.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by etiennelawlor on 1/15/18.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, AndroidModule.class, TestNetworkModule.class, RxModule.class})
public interface TestApplicationComponent extends ApplicationComponent {
    void inject(MovieRemoteDataSourceTest target);
}
