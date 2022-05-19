package com.kuznetsova.mlanning.data

import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.task.Task
import com.kuznetsova.mlanning.domain.taskitem.TaskItem

interface TasksDataSource {

    suspend fun getAllTask(): List<Task>
    suspend fun getTaskById(id: Long): Task
    suspend fun insertTask(task: Task)
    suspend fun updateTask(taskId: Long, name: String, description: String?, priority: TaskPriority)
    suspend fun deleteTask(id: Long)

    suspend fun updateTaskItem(taskItem: TaskItem, taskId: Long)
    suspend fun createTaskItem(taskItem: TaskItem, taskId: Long)
    suspend fun deleteTaskItem(taskItemId: Int)

}