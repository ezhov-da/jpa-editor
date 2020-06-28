package ru.ezhov.jpa.editor.engine;

import java.util.Scanner;

public class GroovyScriptLoader implements ScriptLoader {
    private static final String SCRIPT_FULL = "/script/example-script-full.groovy.txt";

    public String script() {
        return loadScript(SCRIPT_FULL);
    }

    private String loadScript(String resource) {
        Scanner scanner =
                new Scanner(
                        GroovyScriptLoader
                                .class
                                .getResourceAsStream(resource)
                        , "UTF-8"
                );
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
