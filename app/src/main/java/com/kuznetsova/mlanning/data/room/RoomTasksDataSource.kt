package com.kuznetsova.mlanning.data.room

import com.kuznetsova.mlanning.data.TasksDataSource
import com.kuznetsova.mlanning.domain.Day
import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.task.Task
import com.kuznetsova.mlanning.domain.taskitem.TaskItem


class RoomTasksDataSource(private val appDatabase: AppDatabase) : TasksDataSource {
    override suspend fun getAllTask(): List<Task> {

        return appDatabase.taskDAO().getAll().map { taskEntity ->
            transformTask(taskEntity)
        }
    }

    override suspend fun getTaskById(id: Long): Task {
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
                appDatabase.taskItemDAO().getAllByDayId(dayEntity.id)
                    .map { taskItemEntity ->
                        TaskItem(
                            taskItemEntity.id,
                            taskItemEntity.description,
                            Day(dayEntity.day, dayEntity.month, dayEntity.year),
                            taskItemEntity.isDone

                        )
                    }
            },
            priorities.getValue(taskEntity.priorityId)
        )
    }

    override suspend fun insertTask(task: Task) {
        val priority = getPriorityId(task.priority)

        val taskId = appDatabase.taskDAO().insertTask(
            TaskEntity(
                id = task.id,
                name = task.name,
                description = task.description,
                priorityId = priority
            )
        )

        val days = task.taskItems.map { taskItem ->
            taskItem.day
        }.distinct()

        val daysEntity: List<DayEntity> = days.map { it ->
            DayEntity(
                id = 0,
                day = it.day,
                month = it.month,
                year = it.year,
                taskId = taskId
            )
        }

        val daysIds = appDatabase.dayDAO().insertDays(daysEntity)



        appDatabase.taskItemDAO().insertTaskItems(

            task.taskItems.map { taskItem ->
                TaskItemEntity(
                    id = taskItem.id,
                    dayId = daysIds[days.indexOf(taskItem.day)].toInt(),
                    description = taskItem.description,
                    isDone = taskItem.isDone
                )
            }

        )
    }

    override suspend fun updateTask(
        taskId: Long,
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

    private suspend fun getPriorityId(taskPriority: TaskPriority): Int {

        val priority =
            appDatabase.taskPriorityDAO().getAllPriorities().first { taskPriorityEntity ->
                taskPriorityEntity.value == taskPriority.ordinal
            }
        return priority.id
    }

    override suspend fun deleteTask(id: Long) {
        appDatabase.taskDAO().deleteTask(id)
    }

    override suspend fun updateTaskItem(taskItem: TaskItem, taskId: Long) {

        insertTaskItem(taskItem, taskId)
    }

    override suspend fun createTaskItem(taskItem: TaskItem, taskId: Long) {

        insertTaskItem(taskItem, taskId)
    }

    override suspend fun deleteTaskItem(taskItemId: Int) {
        appDatabase.taskItemDAO().deleteTaskItem(taskItemId)
    }

    private suspend fun insertTaskItem(taskItem: TaskItem, taskId: Long) {

        val day: Day = taskItem.day

        val dayEntities: List<DayEntity> = appDatabase.dayDAO().getAllDaysByTaskId(taskId)

        val dayEntity: DayEntity? = dayEntities.firstOrNull {
            it.day == day.day && it.month == day.month && it.year == day.year
        }


        val dayId = if (dayEntity == null) {
            appDatabase.dayDAO().insertDay(
                DayEntity(
                    id = 0,
                    day = day.day,
                    month = day.month,
                    year = day.year,
                    taskId = taskId
                )
            )
        } else {
            dayEntity.id
        }

        appDatabase.taskItemDAO().insertTaskItem(
            TaskItemEntity(
                id = taskItem.id,
                dayId = dayId.toInt(),
                description = taskItem.description,
                isDone = taskItem.isDone
            )
        )
    }
}