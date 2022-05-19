package com.kuznetsova.mlanning.domain.task.interactors

import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.TasksRepository

class UpdateTaskInteractor(private val repository: TasksRepository) {

    suspend fun execute(
        taskId: Long,
        name: String,
        description: String?,
        priority: TaskPriority
    ) {
        repository.updateTask(
            taskId = taskId,
            name = name,
            description = description,
            priority = priority
        )
    }
}