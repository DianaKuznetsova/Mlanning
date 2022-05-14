package com.kuznetsova.mlanning.data.room

import androidx.room.*

@Dao
interface TaskDAO {

    @Query("SELECT * FROM TaskEntity")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Delete(entity = TaskEntity::class)
    suspend fun deleteTask(id: Int)
}