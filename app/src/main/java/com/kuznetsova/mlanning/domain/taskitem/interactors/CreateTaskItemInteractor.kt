package com.kuznetsova.mlanning.domain.taskitem.interactors

import com.kuznetsova.mlanning.domain.Day
import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.taskitem.TaskItem

class CreateTaskItemInteractor(private val repository: TasksRepository) {

    suspend fun execute(description: String, day: Day, taskId: Long) {

        repository.createTaskItem(
            TaskItem(
                id = 0,
                description = description,
                day = day,
                isDone = false
            ),
            taskId
        )
    }
}