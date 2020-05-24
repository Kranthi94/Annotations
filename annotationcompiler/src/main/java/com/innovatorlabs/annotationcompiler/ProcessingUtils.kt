package com.innovatorlabs.annotationcompiler

import com.innovatorlabs.annotations.Todo
import com.innovatorlabs.annotations.TodoPriority
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.util.ElementFilter

class ProcessingUtils {

    companion object {

        fun getTypeElementsToProcess(annotationsSet: MutableSet<out TypeElement>, rootElements: MutableSet<out Element>): MutableSet<out TypeElement> {
            val typeElements: MutableSet<TypeElement> = mutableSetOf()
            for (element in rootElements) {
                if (element is TypeElement) {
                    var found = false
                    for (annotationMirror in element.annotationMirrors) {
                        for (annotation in annotationsSet) {
                            if (annotationMirror.annotationType.asElement() == annotation) {
                                typeElements.add(element)
                                found = true
                                break;
                            }
                        }
                        if (found) break
                    }
                }
            }
            return typeElements
        }

        fun getExecutableElementsToProcess(annotationsSet: MutableSet<out TypeElement>, rootElements: MutableSet<out Element>): MutableSet<out ExecutableElement> {
            val executableElements: MutableSet<ExecutableElement> = mutableSetOf()
            for (rootElement in rootElements) {
                for(element in ElementFilter.methodsIn(rootElement.enclosedElements)) {
                    if (element is ExecutableElement) {
                        var found = false
                        for (annotationMirror in element.annotationMirrors) {
                            for (annotation in annotationsSet) {
                                if (annotationMirror.annotationType.asElement() == annotation) {
                                    executableElements.add(element)
                                    found = true
                                    break;
                                }
                            }
                            if (found) break
                        }
                    }
                }
            }
            return executableElements
        }

        fun getVariableElementsToProcess(annotationsSet: MutableSet<out TypeElement>, rootElements: MutableSet<out Element>): MutableSet<out VariableElement> {
            val variableElements: MutableSet<VariableElement> = mutableSetOf()
            for (rootElement in rootElements) {
                for(element in ElementFilter.fieldsIn(rootElement.enclosedElements)) {
                    if (element is VariableElement) {
                        var found = false
                        for (annotationMirror in element.annotationMirrors) {
                            for (annotation in annotationsSet) {
                                if (annotationMirror.annotationType.asElement() == annotation) {
                                    variableElements.add(element)
                                    found = true
                                    break;
                                }
                            }
                            if (found) break
                        }
                    }
                }
            }
            return variableElements
        }
    }
}