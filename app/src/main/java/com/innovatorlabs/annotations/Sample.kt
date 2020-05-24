package com.innovatorlabs.annotations

@Todo(note = "High Priority", priority = TodoPriority.HIGH)
class Sample {

    @Todo(note = "Medium Priority", priority = TodoPriority.MEDIUM)
    lateinit var text: String

    @Todo(note = "Low Priority", priority = TodoPriority.LOW)
    fun next() {

    }
}