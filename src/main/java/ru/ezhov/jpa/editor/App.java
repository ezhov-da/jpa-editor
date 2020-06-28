package ru.ezhov.jpa.editor;

import ru.ezhov.jpa.editor.configuration.DbService;
import ru.ezhov.jpa.editor.configuration.DbServiceException;
import ru.ezhov.jpa.editor.engine.GroovyEngine;
import ru.ezhov.jpa.editor.engine.GroovyScriptLoader;
import ru.ezhov.jpa.editor.gui.BasicFrame;
import ru.ezhov.jpa.editor.h2db.H2DbService;
import ru.ezhov.jpa.editor.h2db.H2DbServiceException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        try {
            DbService dbService = new DbService();
            dbService.copyDbToUser();

            H2DbService h2DbService = new H2DbService();
            h2DbService.start();
            Runtime.getRuntime().addShutdownHook(new Thread(h2DbService::stop));

//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }

            SwingUtilities.invokeLater(() -> {
                BasicFrame basicFrame = new BasicFrame(new GroovyScriptLoader(), new GroovyEngine());
                basicFrame.setVisible(true);
            });
        } catch (H2DbServiceException | DbServiceException e) {
            e.printStackTrace();
        }
    }
}
