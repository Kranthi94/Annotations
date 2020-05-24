package com.innovatorlabs.annotations

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
@StringDef(TodoPriority.LOW, TodoPriority.MEDIUM, TodoPriority.HIGH)
annotation class TodoPriority {
    companion object {
        const val LOW = "LOW"
        const val MEDIUM = "MEDIUM"
        const val HIGH = "HIGH"
    }
}