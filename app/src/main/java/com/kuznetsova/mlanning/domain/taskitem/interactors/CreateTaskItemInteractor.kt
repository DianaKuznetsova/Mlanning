package com.kuznetsova.mlanning.domain.taskitem.interactors

import com.kuznetsova.mlanning.domain.TasksRepository
import com.kuznetsova.mlanning.domain.taskitem.TaskItem
import java.util.*

class CreateTaskItemInteractor(private val repository: TasksRepository) {

    suspend fun execute(description: String?, day: Date, taskId: Int) {

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