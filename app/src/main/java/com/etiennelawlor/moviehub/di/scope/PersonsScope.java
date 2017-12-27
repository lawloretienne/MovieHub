package com.etiennelawlor.moviehub.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by etiennelawlor on 12/26/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonsScope {
}
