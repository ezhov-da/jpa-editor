package ru.ezhov.engine;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScriptLoaderTest {
    @Test
    public void getImportScript() throws Exception {
        ScriptLoader scriptLoader = new ScriptLoader();
        System.out.println(scriptLoader.getImportScript());
    }

    @Test
    public void getBeginScript() throws Exception {
        ScriptLoader scriptLoader = new ScriptLoader();
        System.out.println(scriptLoader.getBeginScript());
    }

    @Test
    public void getEndScript() throws Exception {
        ScriptLoader scriptLoader = new ScriptLoader();
        System.out.println(scriptLoader.getEndScript());
    }
}