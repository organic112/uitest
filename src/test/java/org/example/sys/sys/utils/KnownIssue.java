package org.example.sys.sys.utils;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface KnownIssue {

    String[] jira() default "";

    String[] testRail() default {};
}
