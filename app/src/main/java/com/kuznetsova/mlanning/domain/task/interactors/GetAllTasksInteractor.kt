package com.kuznetsova.mlanning.domain.task.interactors

import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.task.Task

class GetAllTasksInteractor(private val repository: TasksRepository) {

    suspend fun execute(): List<Task> {
        val tasks = repository.getAll()
        return tasks.sortedBy { it.priority }
    }
}