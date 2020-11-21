package ru.ezhov.jpa.editor.engine

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import groovy.lang.Binding
import groovy.lang.GroovyShell
import java.io.PrintWriter
import java.io.StringWriter

class GroovyEngine : Engine<String, String> {
    override fun execute(source: String): String =
            try {
                val binding = Binding()
                val shell = GroovyShell(binding)
                shell.evaluate(source).toString()
//                val mapper = jacksonObjectMapper()
//                mapper
//                        .writerWithDefaultPrettyPrinter()
//                        .writeValueAsString(value)
            } catch (e: Exception) {
                e.printStackTrace()
                val writer = StringWriter()
                e.printStackTrace(PrintWriter(writer))
                writer.toString()
            }
}
