package org.evoting.application;

public interface EventListener<T> {
    void handle(T event);
}