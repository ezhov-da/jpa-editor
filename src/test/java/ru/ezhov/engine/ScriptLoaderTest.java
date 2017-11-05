package ru.ezhov.engine;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScriptLoaderTest {
    @Test
    public void loadScript() throws Exception {
        ScriptLoader scriptLoader = new ScriptLoader();
        System.out.println(scriptLoader.loadScript());
    }
}