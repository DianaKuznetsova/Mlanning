package com.kuznetsova.mlanning.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskItemDAO {

    @Query("SELECT * FROM TaskItem WHERE dayId = :dayId")
    suspend fun getAllByDayId(dayId: Long): List<TaskItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItems(taskItem: List<TaskItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItem(taskItem: TaskItemEntity)

    @Query("DELETE  FROM TaskItem WHERE id = :id")
    suspend fun deleteTaskItem(id: Int)
}