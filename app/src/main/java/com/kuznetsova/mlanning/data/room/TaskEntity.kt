package com.kuznetsova.mlanning.data.room


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = TaskPriorityEntity::class,
        parentColumns = ["id"],
        childColumns = ["priorityId"]
    )],
    tableName = "Task"
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val description: String?,
    val priorityId: Int
)