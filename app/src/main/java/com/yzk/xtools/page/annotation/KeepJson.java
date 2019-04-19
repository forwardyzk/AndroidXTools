package com.yzk.xtools.page.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by BlingBling on 2018/6/30.
 */

@Retention(CLASS)
@Target({TYPE})
public @interface KeepJson {
}
