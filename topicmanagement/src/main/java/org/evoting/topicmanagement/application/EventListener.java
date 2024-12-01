package org.evoting.topicmanagement.application;

public interface EventListener<T> {
    void handle(T event);
}