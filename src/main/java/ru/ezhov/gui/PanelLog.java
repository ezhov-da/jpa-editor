package ru.ezhov.gui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Scanner;

public class PanelLog extends JPanel {
    private RSyntaxTextArea textAreaLog;
    private RSyntaxTextArea textAreaConsole;


    public PanelLog() {
        setLayout(new BorderLayout());

        textAreaLog = new RSyntaxTextArea(20, 60);
        textAreaLog.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        textAreaLog.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textAreaLog);

        JPanel panelResult = new JPanel(new BorderLayout());
        panelResult.add(sp, BorderLayout.CENTER);


        textAreaConsole = new RSyntaxTextArea(20, 60);
        textAreaConsole.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        textAreaConsole.setCodeFoldingEnabled(true);
        sp = new RTextScrollPane(textAreaConsole);

        JPanel panelConsole = new JPanel(new BorderLayout());
        panelConsole.add(sp, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Result", panelResult);
        tabbedPane.add("Console", panelConsole);

        add(tabbedPane, BorderLayout.CENTER);

        ThreadToTextArea threadToTextArea = new ThreadToTextArea();
        threadToTextArea.setDaemon(true);
        threadToTextArea.start();
    }

    public void setResult(String text) {
        textAreaLog.setText(text);
    }


    private class ThreadToTextArea extends Thread {
        @Override
        public void run() {

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                textAreaConsole.setText(scanner.nextLine());
            }

        }
    }


}
