package com.kuznetsova.mlanning.data.room

import com.kuznetsova.mlanning.data.TasksDataSource
import com.kuznetsova.mlanning.domain.task.Task
import com.kuznetsova.mlanning.domain.taskitem.TaskItem
import com.kuznetsova.mlanning.domain.TaskPriority
import java.util.*

class RoomTasksDataSource(private val appDatabase: AppDatabase) : TasksDataSource {
    override suspend fun getAllTask(): List<Task> {

        return appDatabase.taskDAO().getAll().map { taskEntity ->
            transformTask(taskEntity)
        }
    }

    override suspend fun getTaskById(id: Int): Task {
        val task = appDatabase.taskDAO().getTaskById(id)
        return transformTask(task)
    }

    private suspend fun transformTask(taskEntity: TaskEntity): Task {

        val priorities =
            appDatabase.taskPriorityDAO().getAllPriorities().associate { taskPriorityEntity ->
                taskPriorityEntity.id to TaskPriority.values()[taskPriorityEntity.value]
            }

        return Task(
            taskEntity.id,
            taskEntity.name,
            taskEntity.description,
            appDatabase.dayDAO().getAllDaysByTaskId(taskEntity.id).flatMap { dayEntity ->
                appDatabase.taskItemDAO().getAllByDayId(dayEntity.taskId)
                    .map { taskItemEntity ->
                        TaskItem(
                            taskItemEntity.id,
                            taskItemEntity.description,
                            Date(dayEntity.date),
                            taskItemEntity.isDone

                        )
                    }
            },
            priorities.getValue(taskEntity.priorityId)
        )
    }

    override suspend fun insertTask(task: Task) {
        val priority = getPriorityId(task.priority)

        val days = task.taskItems.map { taskItem ->
            taskItem.day
        }.distinct()

        val daysEntity: List<DayEntity> = days.map { it ->
            DayEntity(
                0,
                it.time,
                task.id
            )
        }

        val daysIds = appDatabase.dayDAO().insertDays(daysEntity)

        appDatabase.taskDAO().insertTask(
            TaskEntity(
                task.id,
                task.name,
                task.description,
                priority
            )
        )

        appDatabase.taskItemDAO().insertTaskItems(

            task.taskItems.map { taskItem ->
                TaskItemEntity(
                    taskItem.id,
                    daysIds[days.indexOf(taskItem.day)],
                    taskItem.description,
                    taskItem.isDone
                )
            }

        )
    }

    override suspend fun updateTask(
        taskId: Int,
        name: String,
        description: String?,
        priority: TaskPriority
    ) {

        appDatabase.taskDAO().insertTask(
            TaskEntity(
                id = taskId,
                name = name,
                description = description,
                priorityId = getPriorityId(priority)
            )
        )
    }

    private suspend fun getPriorityId(taskPriority: TaskPriority): Int{

        val priority =
            appDatabase.taskPriorityDAO().getAllPriorities().first { taskPriorityEntity ->
                taskPriorityEntity.value == taskPriority.ordinal
            }
        return priority.id
    }

    override suspend fun deleteTask(id: Int) {
        appDatabase.taskDAO().deleteTask(id)
    }

    override suspend fun updateTaskItem(taskItem: TaskItem, taskId: Int) {

        insertTaskItem(taskItem, taskId)
    }

    override suspend fun createTaskItem(taskItem: TaskItem, taskId: Int) {

        insertTaskItem(taskItem, taskId)
    }

    override suspend fun deleteTaskItem(taskItemId: Int) {
        appDatabase.taskItemDAO().deleteTaskItem(taskItemId)
    }

    private suspend fun insertTaskItem(taskItem: TaskItem, taskId: Int){

        val day: Date = taskItem.day

        val dayEntities: List<DayEntity> = appDatabase.dayDAO().getAllDaysByTaskId(taskId)

        val dayEntity: DayEntity? = dayEntities.firstOrNull{
            it.date == day.time
        }


        val dayId = if(dayEntity == null){
            appDatabase.dayDAO().insertDay(DayEntity(
                id = 0,
                date = day.time,
                taskId = taskId
            ))
        }else {
            dayEntity.id
        }

        appDatabase.taskItemDAO().insertTaskItem(
            TaskItemEntity(
                id = taskItem.id,
                dayId = dayId,
                description = taskItem.description,
                isDone = taskItem.isDone
            )
        )
    }
}