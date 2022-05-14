package com.kuznetsova.mlanning.domain.taskitem.interactors

import com.kuznetsova.mlanning.domain.TasksRepository

class DeleteTaskItemInteractor(val repository: TasksRepository) {

    suspend fun execute(id: Int) {
        repository.deleteTaskItem(id)
    }
}