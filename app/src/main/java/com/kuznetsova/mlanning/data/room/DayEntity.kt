package com.kuznetsova.mlanning.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Day"
)
data class DayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val day: Int,
    val month: Int,
    val year: Int,
    val taskId: Long
)
