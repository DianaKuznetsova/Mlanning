package com.kuznetsova.mlanning.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Day"
)
data class DayEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: Long,
    val taskId: Int
)
