package com.kuznetsova.mlanning.data.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = TaskEntity::class,
        parentColumns = ["id"],
        childColumns = ["taskId"],
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "Notification"
)
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val taskId: Int,
    val time: Long
)