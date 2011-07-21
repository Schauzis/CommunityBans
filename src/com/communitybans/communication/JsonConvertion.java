package com.communitybans.communication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Hretsam
 * 
 * This annotation is used to check what methods the JSON converted should use to convert
 * the communicationobjects to JSON and back.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonConvertion {

    String name();

    String type();
}
