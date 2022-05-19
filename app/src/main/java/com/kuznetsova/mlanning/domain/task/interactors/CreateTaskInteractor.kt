package com.kuznetsova.mlanning.domain.task.interactors

import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.task.Task
import com.kuznetsova.mlanning.domain.taskitem.PartialTaskItem
import com.kuznetsova.mlanning.domain.taskitem.TaskItem

class CreateTaskInteractor(private val repository: TasksRepository) {

    suspend fun execute(
        name: String,
        description: String?,
        partialTaskItems: List<PartialTaskItem>,
        priority: TaskPriority
    ) {
        val taskItems: List<TaskItem> = partialTaskItems.map { it ->
            TaskItem(
                0,
                it.description,
                it.day,
                false
            )
        }

        repository.insertTask(
            Task(
                id = 0,
                name = name,
                description = description,
                taskItems = taskItems,
                priority = priority
            )
        )
    }
}