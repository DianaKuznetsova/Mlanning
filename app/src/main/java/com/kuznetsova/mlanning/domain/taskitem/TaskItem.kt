package com.kuznetsova.mlanning.domain.taskitem

import java.util.*

/**
 * Subtask
 */
data class TaskItem(
    val id: Int,
    val description: String?,
    val day: Date,
    val isDone: Boolean
)
