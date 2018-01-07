package org.deshand.service;

/**
 * @author Roland Krüger
 */
public interface VaadinUIService {
    void postNavigationEvent(Object source, String target);

    boolean isUserAnonymous();
}
