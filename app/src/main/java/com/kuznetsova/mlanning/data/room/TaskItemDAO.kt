package com.kuznetsova.mlanning.data.room

import androidx.room.*

@Dao
interface TaskItemDAO {

    @Query("SELECT * FROM TaskItem WHERE dayId = :dayId")
    suspend fun getAllByDayId(dayId: Int): List<TaskItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItems(taskItem: List<TaskItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItem(taskItem: TaskItemEntity)

    @Delete(entity = TaskItemEntity::class)
    suspend fun deleteTaskItem(id: Int)
}