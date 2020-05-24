package com.innovatorlabs.annotations

class Sample2 {

    @Todo(note = "Low Priority", priority = TodoPriority.LOW)
    lateinit var a: String

    @Todo(note = "High Priority", priority = TodoPriority.HIGH)
    fun high() {

    }
}