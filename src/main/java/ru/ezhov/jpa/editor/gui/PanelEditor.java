package ru.ezhov.jpa.editor.gui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.jpa.editor.engine.ScriptLoader;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PanelEditor extends JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(PanelEditor.class.getName());

    private final ScriptLoader scriptLoader;

    private RSyntaxComponent editor;

    public PanelEditor(ScriptLoader scriptLoader) {
        this.scriptLoader = scriptLoader;

        this.setLayout(new BorderLayout());

        init();
    }

    private void init() {
        editor = createTextArea();
        editor.getSyntaxTextArea().setText(scriptLoader.script());

        add(editor.getScrollPane(), BorderLayout.CENTER);
    }

    public void reloadScript() {
        editor.getSyntaxTextArea().setText(scriptLoader.script());
    }

    private RSyntaxComponent createTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        return new RSyntaxComponent(textArea, sp);
    }

    public String getScriptForExecute() {
        LOG.debug("Text for execute:\n{}", editor.getSyntaxTextArea().getText());

        return editor.getSyntaxTextArea().getText();
    }

    private static class RSyntaxComponent {
        private final RSyntaxTextArea syntaxTextArea;
        private final RTextScrollPane scrollPane;

        public RSyntaxComponent(RSyntaxTextArea syntaxTextArea, RTextScrollPane scrollPane) {
            this.syntaxTextArea = syntaxTextArea;
            this.scrollPane = scrollPane;
        }

        public RSyntaxTextArea getSyntaxTextArea() {
            return syntaxTextArea;
        }

        public RTextScrollPane getScrollPane() {
            return scrollPane;
        }
    }
}