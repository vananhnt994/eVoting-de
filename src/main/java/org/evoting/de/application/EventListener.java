package org.evoting.de.application;

public interface EventListener<T> {
    void handle(T event);
}