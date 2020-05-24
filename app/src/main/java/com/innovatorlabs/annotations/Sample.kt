package com.innovatorlabs.annotations

@Todo(note = "High Priority", priority = TodoPriority.HIGH)
class Sample {

    @Todo(note = "Medium Priority", priority = TodoPriority.MEDIUM)
    lateinit var text: String
}