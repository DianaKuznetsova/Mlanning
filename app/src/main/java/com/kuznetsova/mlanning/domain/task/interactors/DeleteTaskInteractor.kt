package com.kuznetsova.mlanning.domain.task.interactors

import com.kuznetsova.mlanning.domain.TasksRepository

class DeleteTaskInteractor(private val repository: TasksRepository) {

    suspend fun execute(id: Long) {

        repository.deleteTask(id)
    }
}