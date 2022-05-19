package com.kuznetsova.mlanning.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDAO {

    @Query("SELECT * FROM Notification WHERE taskId = :taskId")
    suspend fun getNotificationByTaskId(taskId: Int): NotificationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationEntity)

    @Query("DELETE  FROM Notification WHERE id = :notificationId")
    suspend fun deleteNotification(notificationId: Int)
}