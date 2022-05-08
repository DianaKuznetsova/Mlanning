package com.kuznetsova.mlanning.domain

class GetAllTasksInteractor(private val repository: TasksRepository) {

    suspend fun execute(): List<Task>{
        val tasks = repository.getAll()
        return tasks.sortedBy { it.priority }
    }
}