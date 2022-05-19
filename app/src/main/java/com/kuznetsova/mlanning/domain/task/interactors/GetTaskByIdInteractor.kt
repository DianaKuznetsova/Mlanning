package com.kuznetsova.mlanning.domain.task.interactors

import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.task.Task

class GetTaskByIdInteractor(private val repository: TasksRepository) {

    suspend fun execute(id: Long): Task {
        return repository.getTaskById(id)
    }
}