package com.kuznetsova.mlanning.domain

class InsertTaskInteractor(private val repository: TasksRepository) {

    suspend fun execute(
        name: String,
        description: String?,
        taskItems: List<TaskItem>,
        priority: TaskPriority
    ){
       repository.insertTask(Task(
           id = 0,
           name = name,
           description = description,
           taskItems = taskItems,
           priority = priority
       ))
    }
}