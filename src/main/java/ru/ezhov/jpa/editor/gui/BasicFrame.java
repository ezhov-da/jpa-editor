package ru.ezhov.jpa.editor.gui;

import ru.ezhov.jpa.editor.engine.Engine;
import ru.ezhov.jpa.editor.engine.ScriptLoader;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.IOException;


public class BasicFrame extends JFrame {
    private final Engine<String, String> engine;
    private final PanelEditor panelEditor;
    private final PanelLog panelLog;

    public BasicFrame(ScriptLoader scriptLoader, Engine<String, String> engine) throws HeadlessException {
        this.engine = engine;
        this.panelEditor = new PanelEditor(scriptLoader);
        this.panelLog = new PanelLog();

        init();
    }

    private void init() {
        try {
            setIconImage(ImageIO.read(BasicFrame.class.getResourceAsStream("/editor.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(new PanelButton(), BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(panelEditor);
        splitPane.setBottomComponent(panelLog);
        splitPane.setDividerLocation(0.8);
        splitPane.setResizeWeight(0.8);

        add(splitPane, BorderLayout.CENTER);

        setTitle("JPA editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    private class PanelButton extends JPanel {
        public PanelButton() {
            JButton buttonOpenScriptExample = new JButton("Reset example");

            buttonOpenScriptExample.addActionListener(e -> panelEditor.reloadScript());

            JButton buttonExecuteScript = new JButton("Execute script");
            buttonExecuteScript.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    try {
                        String val = engine.execute(panelEditor.getScriptForExecute());
                        panelLog.setResult(val);
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        JOptionPane.showMessageDialog(
                                BasicFrame.this,
                                "Error execute script",
                                "Error", JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            });

            add(buttonOpenScriptExample);
            add(buttonExecuteScript);
        }
    }
}
