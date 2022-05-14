package com.kuznetsova.mlanning.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "TaskPriority"
)
data class TaskPriorityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val value: Int
)
