package ru.ezhov.gui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.engine.ScriptLoader;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class PanelEditor extends JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(PanelEditor.class.getName());

    @Inject
    private ScriptLoader scriptLoader;

    private RSyntaxComponent textAreaImportDefault;
    private RSyntaxComponent textAreaImportUser;
    private RSyntaxComponent textAreaBeginDefault;
    private RSyntaxComponent textAreaUserCode;
    private RSyntaxComponent textAreaEndDefault;

    private PanelImport panelImport;
    private PanelBegin panelBegin;
    private PanelEnd panelEnd;

    private JLabel labelDefaultImport = new JLabel();
    private JLabel labelBeginDefault = new JLabel();
    private JLabel labelEndDefault = new JLabel();

    public PanelEditor() {
        setLayout(new BorderLayout());

        textAreaImportDefault = createTextArea();
        textAreaImportDefault.getSyntaxTextArea().setEditable(false);
        textAreaImportDefault.getSyntaxTextArea().setEnabled(false);

        textAreaImportUser = createTextArea();

        textAreaBeginDefault = createTextArea();
        textAreaBeginDefault.getSyntaxTextArea().setEnabled(false);
        textAreaBeginDefault.getSyntaxTextArea().setEditable(false);

        textAreaUserCode = createTextArea();

        textAreaEndDefault = createTextArea();
        textAreaEndDefault.getSyntaxTextArea().setEditable(false);
        textAreaEndDefault.getSyntaxTextArea().setEnabled(false);

        panelImport = new PanelImport();
        panelBegin = new PanelBegin();
        panelEnd = new PanelEnd();

    }

    @PostConstruct
    private void postInit() {
        String s = "<html>" + scriptLoader.getImportScript().replace("\n", "<br/>");
        labelDefaultImport.setText(s);
        labelDefaultImport.setBorder(BorderFactory.createTitledBorder("Default import"));

        s = "<html>" + scriptLoader.getBeginScript().replace("\n", "<br/>");
        labelBeginDefault.setText(s);
        labelBeginDefault.setBorder(BorderFactory.createTitledBorder("Default begin"));

        s = "<html>" + scriptLoader.getEndScript().replace("\n", "<br/>");
        labelEndDefault.setText(s);
        labelEndDefault.setBorder(BorderFactory.createTitledBorder("Default end"));

        textAreaUserCode.getSyntaxTextArea().setText(scriptLoader.getUserScript());

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(panelImport);
        splitPane.setBottomComponent(panelBegin);

        add(splitPane, BorderLayout.CENTER);
        add(panelEnd, BorderLayout.SOUTH);
    }

    public void reloadScript() {
        textAreaImportUser.getSyntaxTextArea().setText("");
        textAreaUserCode.getSyntaxTextArea().setText(scriptLoader.getUserScript());
    }

    private RSyntaxComponent createTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        return new RSyntaxComponent(textArea, sp);
    }

    public String getScriptForExecute() {
        String text =
                scriptLoader.getImportScript() +
                        textAreaImportUser.getSyntaxTextArea().getText() +
                        scriptLoader.getBeginScript() +
                        textAreaUserCode.getSyntaxTextArea().getText() +
                        scriptLoader.getEndScript();

        LOG.debug("Text for execute:\n{}", text);

        return text;
    }

    private class PanelImport extends JPanel {
        public PanelImport() {
            super(new BorderLayout());

            textAreaImportDefault.getScrollPane().setBorder(BorderFactory.createLineBorder(Color.CYAN));
            add(labelDefaultImport, BorderLayout.NORTH);

            textAreaImportUser.getScrollPane().setBorder(BorderFactory.createTitledBorder("User import"));
            add(textAreaImportUser.getScrollPane(), BorderLayout.CENTER);
        }
    }

    private class PanelBegin extends JPanel {
        public PanelBegin() {
            super(new BorderLayout());
            add(labelBeginDefault, BorderLayout.NORTH);

            textAreaUserCode.getScrollPane().setBorder(BorderFactory.createTitledBorder("User code"));

            add(textAreaUserCode.getScrollPane(), BorderLayout.CENTER);
        }
    }

    private class PanelEnd extends JPanel {
        public PanelEnd() {
            super(new BorderLayout());
            add(labelEndDefault, BorderLayout.CENTER);
        }
    }

    private class RSyntaxComponent {
        private RSyntaxTextArea syntaxTextArea;
        private RTextScrollPane scrollPane;

        public RSyntaxComponent(RSyntaxTextArea syntaxTextArea, RTextScrollPane scrollPane) {
            this.syntaxTextArea = syntaxTextArea;
            this.scrollPane = scrollPane;
        }

        public RSyntaxTextArea getSyntaxTextArea() {
            return syntaxTextArea;
        }

        public void setSyntaxTextArea(RSyntaxTextArea syntaxTextArea) {
            this.syntaxTextArea = syntaxTextArea;
        }

        public RTextScrollPane getScrollPane() {
            return scrollPane;
        }

        public void setScrollPane(RTextScrollPane scrollPane) {
            this.scrollPane = scrollPane;
        }
    }
}