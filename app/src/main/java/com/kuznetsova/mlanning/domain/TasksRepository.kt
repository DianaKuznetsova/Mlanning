package com.kuznetsova.mlanning.domain

interface TasksRepository {

    suspend fun getAll(): List<Task>
    suspend fun getTaskById(id: Int): Task
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(id: Int)


}