package com.kuznetsova.mlanning.data

import com.kuznetsova.mlanning.domain.task.Task
import com.kuznetsova.mlanning.domain.taskitem.TaskItem
import com.kuznetsova.mlanning.domain.TaskPriority

interface TasksDataSource {

    suspend fun getAllTask(): List<Task>
    suspend fun getTaskById(id: Int): Task
    suspend fun insertTask(task: Task)
    suspend fun updateTask(taskId: Int, name: String, description: String?, priority: TaskPriority)
    suspend fun deleteTask(id: Int)

    suspend fun updateTaskItem(taskItem: TaskItem, taskId: Int)
    suspend fun createTaskItem(taskItem: TaskItem, taskId: Int)
    suspend fun deleteTaskItem(taskItemId: Int)

}