package com.kuznetsova.mlanning.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TaskPriorityDAO {

    @Query("SELECT * FROM TaskPriority")
    suspend fun getAllPriorities() :List<TaskPriorityEntity>
}