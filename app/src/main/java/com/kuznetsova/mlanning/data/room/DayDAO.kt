package com.kuznetsova.mlanning.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DayDAO {

    @Query("SELECT * FROM Day WHERE taskId = :taskId")
    suspend fun getAllDaysByTaskId(taskId: Long): List<DayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(day: List<DayEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity): Long

    @Query("DELETE  FROM Day WHERE id = :dayId")
    suspend fun deleteDay(dayId: Int)


}