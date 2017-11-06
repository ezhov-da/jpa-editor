package ru.ezhov.gui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.engine.Engine;
import ru.ezhov.engine.Groovy;
import ru.ezhov.engine.ScriptLoader;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class BasicFrame extends JFrame {


    @Inject
    @Groovy
    private Engine<String, String> engine;

    @Inject
    private PanelEditor panelEditor;

    @Inject
    private PanelLog panelLog;

    private JButton buttonOpenScriptExample;
    private JButton buttonExecuteScript;


    @PostConstruct
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

        add(splitPane, BorderLayout.CENTER);

        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(0.5);

        setTitle("JPA editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    private class PanelButton extends JPanel {

        public PanelButton() {
            buttonOpenScriptExample = new JButton("Open script example");

            buttonOpenScriptExample.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelEditor.reloadScript();
                }

            });

            buttonExecuteScript = new JButton("Execute script");
            buttonExecuteScript.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
                }
            });

            add(buttonOpenScriptExample);
            add(buttonExecuteScript);

        }
    }
}
