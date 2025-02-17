package com.github.pedropareja.database.generic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface DBTableInfo
{
    String database() default "";
    String schema() default "";
    String table() default "";
}
