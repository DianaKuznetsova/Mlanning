package com.kuznetsova.mlanning.domain.taskitem.interactors

import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.taskitem.TaskItem

class UpdateTaskItemInteractor(private val repository: TasksRepository) {

    suspend fun execute(taskItem: TaskItem, taskId: Long) {
        repository.updateTaskItem(taskItem, taskId)
    }
}