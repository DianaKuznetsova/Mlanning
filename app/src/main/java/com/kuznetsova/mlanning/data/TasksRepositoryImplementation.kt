package com.kuznetsova.mlanning.data

import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.task.Task
import com.kuznetsova.mlanning.domain.taskitem.TaskItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TasksRepositoryImplementation(
    private val localDataSource: TasksDataSource,
    private val coroutineContext: CoroutineContext = Dispatchers.IO
) : TasksRepository {
    override suspend fun getAll(): List<Task> = withContext(coroutineContext) {
        return@withContext localDataSource.getAllTask()
    }

    override suspend fun getTaskById(id: Int): Task = withContext(coroutineContext) {
        return@withContext localDataSource.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) = withContext(coroutineContext) {
        return@withContext localDataSource.insertTask(task)
    }

    override suspend fun updateTask(
        taskId: Int,
        name: String,
        description: String?,
        priority: TaskPriority
    ) {
        localDataSource.updateTask(
            taskId = taskId,
            name = name,
            description = description,
            priority = priority
        )
    }

    override suspend fun deleteTask(id: Int) = withContext(coroutineContext) {
        localDataSource.deleteTask(id)
    }

    override suspend fun updateTaskItem(taskItem: TaskItem, taskId: Int) {
        localDataSource.updateTaskItem(taskItem, taskId)
    }

    override suspend fun createTaskItem(taskItem: TaskItem, taskId: Int) {
        localDataSource.createTaskItem(taskItem, taskId)
    }

    override suspend fun deleteTaskItem(id: Int) = withContext(coroutineContext) {
        localDataSource.deleteTaskItem(id)
    }

    override suspend fun getAllTaskItemByDayId(dayId: Int) = withContext(coroutineContext) {
        TODO("Not yet implemented")
    }
}