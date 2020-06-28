package ru.ezhov.jpa.editor.engine;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyEngine implements Engine<String, String> {
    public String execute(String source) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        Object value = shell.evaluate(source);

        return value.toString();
    }
}
