package com.thp.mvp.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by thp on 2019/6/1.
 */

@Documented
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
