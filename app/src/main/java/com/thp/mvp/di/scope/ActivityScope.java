package com.thp.mvp.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by thp 2019-5-24
 */

@Documented
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
