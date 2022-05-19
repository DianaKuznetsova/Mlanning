package com.kuznetsova.mlanning.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDAO {

    @Query("SELECT * FROM Task")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: Long): TaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Query("DELETE  FROM Task WHERE id = :id")
    suspend fun deleteTask(id: Long)
}