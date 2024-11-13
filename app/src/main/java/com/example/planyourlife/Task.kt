package com.example.planyourlife

import java.time.LocalDate


@Suppress("PLUGIN_IS_NOT_ENABLED")
data class Task(
    val name: String,
    val id: Int,
    val jobType: String,       // Type of job, e.g., "Meeting", "Deadline"
    val date: LocalDate? = null,         // Number of hours the task takes
    val description: String,
    var isCompleted: Boolean    // A description of the task
)
