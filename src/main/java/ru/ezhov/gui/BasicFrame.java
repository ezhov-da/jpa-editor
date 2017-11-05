package ru.ezhov.gui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.engine.Engine;
import ru.ezhov.engine.ScriptLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BasicFrame extends JFrame {

    private RSyntaxTextArea textAreaCode;
    private RSyntaxTextArea textAreaLog;
    private JButton buttonOpenScriptExample;
    private JButton buttonExecuteScript;

    private Engine<String, String> engine;

    public BasicFrame(Engine<String, String> engine) {
        this.engine = engine;
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
        splitPane.setTopComponent(new PanelEditor());
        splitPane.setBottomComponent(new PanelLog());

        add(splitPane, BorderLayout.CENTER);

        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(0.5);

        setTitle("JPA editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private class PanelEditor extends JPanel {

        public PanelEditor() {
            setLayout(new BorderLayout());

            textAreaCode = new RSyntaxTextArea(20, 60);
            textAreaCode.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            textAreaCode.setCodeFoldingEnabled(true);
            RTextScrollPane sp = new RTextScrollPane(textAreaCode);
            add(sp);
        }
    }

    private class PanelButton extends JPanel {

        public PanelButton() {
            buttonOpenScriptExample = new JButton("Open script example");

            buttonOpenScriptExample.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textAreaCode.setText(new ScriptLoader().loadScript());
                }

            });

            buttonExecuteScript = new JButton("Execute script");
            buttonExecuteScript.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String val = engine.execute(textAreaCode.getText());
                        textAreaLog.setText(val);
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

    private class PanelLog extends JPanel {

        public PanelLog() {
            setLayout(new BorderLayout());

            textAreaLog = new RSyntaxTextArea(20, 60);
            textAreaLog.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
            textAreaLog.setCodeFoldingEnabled(true);
            RTextScrollPane sp = new RTextScrollPane(textAreaLog);
            add(sp);
        }
    }
}
