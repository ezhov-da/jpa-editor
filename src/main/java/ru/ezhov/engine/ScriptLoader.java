package ru.ezhov.engine;

import java.util.Scanner;

public class ScriptLoader {
    public String loadScript() {
        Scanner scanner =
                new Scanner(
                        ScriptLoader
                                .class
                                .getResourceAsStream("/example-script.groovy")
                        , "UTF-8"
                );
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
