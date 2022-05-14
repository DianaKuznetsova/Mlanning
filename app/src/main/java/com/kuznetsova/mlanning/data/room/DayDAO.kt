package com.kuznetsova.mlanning.data.room

import androidx.room.*

@Dao
interface DayDAO {

    @Query("SELECT * FROM Day WHERE taskId = :taskId")
    suspend fun getAllDaysByTaskId(taskId: Int): List<DayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(day: List<DayEntity>): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity): Int

    @Delete(entity = DayEntity::class)
    suspend fun deleteDay(dayId: Int)


}