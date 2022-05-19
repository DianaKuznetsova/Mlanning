package com.kuznetsova.mlanning.domain.task

import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.taskitem.TaskItem

data class Task(
    val id: Long,
    val name: String,
    val description: String?,
    val taskItems: List<TaskItem>,
    val priority: TaskPriority,
)