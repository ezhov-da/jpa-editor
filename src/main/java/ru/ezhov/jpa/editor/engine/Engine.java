package ru.ezhov.jpa.editor.engine;

public interface Engine<T, V> {
    V execute(T source) throws Exception;
}
