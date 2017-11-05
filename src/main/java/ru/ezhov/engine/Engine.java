package ru.ezhov.engine;

public interface Engine<T, V> {
    V execute(T source) throws Throwable;
}
