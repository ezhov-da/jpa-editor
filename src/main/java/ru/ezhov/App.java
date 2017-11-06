package ru.ezhov;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import ru.ezhov.gui.BasicFrame;

import javax.inject.Inject;
import javax.swing.*;

public class App {

    @Inject
    private BasicFrame basicFrame;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();

        App app = weldContainer.select(App.class).get();
        app.startApp();
    }


    public void startApp() {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {


                basicFrame.setVisible(true);
            }
        });
    }
}
