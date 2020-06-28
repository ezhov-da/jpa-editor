package ru.ezhov.jpa.editor.gui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.Scanner;

public class PanelLog extends JPanel {
    private final RSyntaxTextArea textAreaLog;
    private final RSyntaxTextArea textAreaConsole;

    public PanelLog() {
        setLayout(new BorderLayout());

        this.textAreaLog = new RSyntaxTextArea(20, 60);
        this.textAreaLog.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        this.textAreaLog.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textAreaLog);

        JPanel panelResult = new JPanel(new BorderLayout());
        panelResult.add(sp, BorderLayout.CENTER);

        this.textAreaConsole = new RSyntaxTextArea(20, 60);
        this.textAreaConsole.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        this.textAreaConsole.setCodeFoldingEnabled(true);
        sp = new RTextScrollPane(textAreaConsole);

        JPanel panelConsole = new JPanel(new BorderLayout());
        panelConsole.add(sp, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Result", panelResult);
//        tabbedPane.add("Console", panelConsole);

        add(tabbedPane, BorderLayout.CENTER);

//        ThreadToTextArea threadToTextArea = new ThreadToTextArea();
//        threadToTextArea.setDaemon(true);
//        threadToTextArea.start();
    }

    public void setResult(String text) {
        textAreaLog.setText(text);
    }


    private class ThreadToTextArea extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                SwingUtilities.invokeLater(() -> {
                    textAreaConsole.setText(scanner.nextLine());
                });
            }
        }
    }
}
