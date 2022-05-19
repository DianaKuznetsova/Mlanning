package com.kuznetsova.mlanning.domain.taskitem

import com.kuznetsova.mlanning.domain.Day


/**
 * Subtask
 */
data class TaskItem(
    val id: Int,
    val description: String,
    val day: Day,
    val isDone: Boolean
)
