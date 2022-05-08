package com.kuznetsova.mlanning.domain

class DeleteTaskInteractor(private val repository: TasksRepository) {

    suspend fun execute(id: Int){

        repository.deleteTask(id)
    }
}