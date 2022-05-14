package com.kuznetsova.mlanning.data.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = DayEntity::class,
        parentColumns = ["id"],
        childColumns = ["dayId"],
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "TaskItem"
)
data class TaskItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dayId: Int,
    val description: String?,
    val isDone: Boolean
)
