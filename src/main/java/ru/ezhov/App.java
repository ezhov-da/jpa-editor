package ru.ezhov;

import ru.ezhov.engine.GroovyEngine;
import ru.ezhov.gui.BasicFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                BasicFrame basicFrame = new BasicFrame(new GroovyEngine());
                basicFrame.setVisible(true);
            }
        });


    }
}
