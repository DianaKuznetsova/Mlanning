package com.kuznetsova.mlanning.domain

data class Task(
    val id: Int,
    val name: String,
    val description: String?,
    val taskItems: List<TaskItem>,
    val priority: TaskPriority,




)