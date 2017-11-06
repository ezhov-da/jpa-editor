package ru.ezhov.engine;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

@Groovy
public class GroovyEngine implements Engine<String, String> {
    public String execute(String source) {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        Object value = shell.evaluate(source);

        return value.toString();
    }
}
