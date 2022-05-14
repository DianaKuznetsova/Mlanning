package com.kuznetsova.mlanning.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class, TaskItemEntity::class, DayEntity::class, NotificationEntity::class, TaskPriorityEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO
    abstract fun taskItemDAO(): TaskItemDAO
    abstract fun dayDAO(): DayDAO
    abstract fun notificationDAO(): NotificationDAO
    abstract fun taskPriorityDAO(): TaskPriorityDAO
}