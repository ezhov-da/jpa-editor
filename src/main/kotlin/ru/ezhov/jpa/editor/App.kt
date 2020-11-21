package ru.ezhov.jpa.editor

import ru.ezhov.jpa.editor.configuration.DbService
import ru.ezhov.jpa.editor.configuration.DbServiceException
import ru.ezhov.jpa.editor.engine.GroovyEngine
import ru.ezhov.jpa.editor.engine.GroovyScriptLoader
import ru.ezhov.jpa.editor.gui.BasicFrame
import ru.ezhov.jpa.editor.h2db.H2DbService
import ru.ezhov.jpa.editor.h2db.H2DbServiceException
import javax.swing.SwingUtilities


fun main() {
    try {
        val dbService = DbService()
        dbService.copyDbToUser()
        val h2DbService = H2DbService()
        h2DbService.start()
        Runtime.getRuntime().addShutdownHook(Thread { h2DbService.stop() })

        SwingUtilities.invokeLater {
            val basicFrame = BasicFrame(GroovyScriptLoader(), GroovyEngine())
            basicFrame.isVisible = true
        }
    } catch (e: H2DbServiceException) {
        e.printStackTrace()
    } catch (e: DbServiceException) {
        e.printStackTrace()
    }
}
