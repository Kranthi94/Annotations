package com.innovatorlabs.annotationcompiler

import com.google.auto.service.AutoService
import com.innovatorlabs.annotations.Todo
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class TodoProcessor : AbstractProcessor() {

    override fun process(annotationsSet: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        var todosFound = false
        if (roundEnvironment.processingOver()) {
            return todosFound
        }
        Logger.getAnonymousLogger().log(Level.SEVERE, "Todo's which needs to be resolved")
        roundEnvironment.rootElements.forEach { rootElement ->
            val typeElements = ProcessingUtils.getTypeElementsToProcess(annotationsSet, mutableSetOf(rootElement))
            val executableElements = ProcessingUtils.getExecutableElementsToProcess(annotationsSet, mutableSetOf(rootElement))
            val variableElements = ProcessingUtils.getVariableElementsToProcess(annotationsSet, mutableSetOf(rootElement))
            if(!todosFound && (typeElements.isNotEmpty() || executableElements.isNotEmpty() || variableElements.isNotEmpty())) {
                todosFound = true
            }
            typeElements.forEach { typeElement ->
                Logger.getAnonymousLogger().log(Level.SEVERE, "######################################################################")
                Logger.getAnonymousLogger().log(Level.SEVERE, String.format("%S  :  %S,  %S  :  %S", "Class", typeElement.simpleName, "Priority", typeElement.getAnnotation(Todo::class.java).priority))
            }
            if (typeElements.isEmpty() && (executableElements.isNotEmpty() || variableElements.isNotEmpty())) {
                Logger.getAnonymousLogger().log(Level.SEVERE, "######################################################################")
                Logger.getAnonymousLogger().log(Level.SEVERE, String.format("%S  :  %S", "Class", rootElement.simpleName))
            }
            executableElements.forEach { executableElement ->
                Logger.getAnonymousLogger().log(Level.SEVERE, String.format("%S  :  %S,  %S  :  %S", "Method", executableElement.simpleName, "Priority", executableElement.getAnnotation(Todo::class.java).priority))
            }
            variableElements.forEach { variableElement ->
                Logger.getAnonymousLogger().log(Level.SEVERE, String.format("%S  :  %S,  %S  :  %S", "Variable", variableElement.simpleName, "Priority", variableElement.getAnnotation(Todo::class.java).priority))
            }
        }
        if (todosFound) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "######################################################################")
            try {
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Compilation Error - Resolve the Todos")
            } catch (e: IOException) {
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, e.printStackTrace().toString())
            }
        }
        return todosFound
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Todo::class.java.name)
    }


}
