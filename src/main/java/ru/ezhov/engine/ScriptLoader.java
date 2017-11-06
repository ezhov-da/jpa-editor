package ru.ezhov.engine;

import java.util.Scanner;

public class ScriptLoader {
    private static final String SCRIPT_IMPORT = "/scripts/example-script-import.groovy";
    private static final String SCRIPT_BEGIN = "/scripts/example-script-begin.groovy";
    private static final String SCRIPT_USER = "/scripts/example-script-user.groovy";
    private static final String SCRIPT_END = "/scripts/example-script-end.groovy";

    public String getImportScript() {
        return loadScript(SCRIPT_IMPORT);
    }

    public String getBeginScript() {
        return loadScript(SCRIPT_BEGIN);
    }

    public String getEndScript() {
        return loadScript(SCRIPT_END);
    }

    public String getUserScript() {
        return loadScript(SCRIPT_USER);
    }


    private String loadScript(String file) {
        Scanner scanner =
                new Scanner(
                        ScriptLoader
                                .class
                                .getResourceAsStream(file)
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
