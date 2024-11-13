package com.example.planyourlife

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Suppress("PLUGIN_IS_NOT_ENABLED")
data class Day(
    val date: LocalDate,        // The date represented by this object
    val tasks: MutableList<Task> = mutableListOf()  // List of tasks for the day
)


// Define route with a 'day' argument
const val DAY_ROUTE = "day_route"
const val DAY_ARG = "day_json"
