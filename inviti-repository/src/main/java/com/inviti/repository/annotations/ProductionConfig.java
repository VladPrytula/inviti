package com.inviti.repository.annotations;

/**
 * Created by vladyslavprytula on 8/14/14.
 */


import java.lang.annotation.Retention;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by vladyslavprytula on 8/13/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ProductionConfig {
}
