package org.evoting.citizenmanagement.application;

public interface EventListener<T> {
    void handle(T event);
}