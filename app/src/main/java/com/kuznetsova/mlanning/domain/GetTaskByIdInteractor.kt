package com.kuznetsova.mlanning.domain

class GetTaskByIdInteractor(private val repository: TasksRepository) {

    suspend fun execute(id: Int): Task{
       return repository.getTaskById(id)
    }
}