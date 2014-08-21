package com.inviti.service.metricservice;



import java.util.Set;

/**
 * Created by vladyslavprytula on 8/18/14.
 */
public interface Proximity<T> {
    Set<T> getNearby(T entity);
}
