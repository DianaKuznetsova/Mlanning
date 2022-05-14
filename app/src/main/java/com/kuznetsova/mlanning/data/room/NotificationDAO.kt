package com.kuznetsova.mlanning.data.room

import androidx.room.*

@Dao
interface NotificationDAO {

    @Query("SELECT * FROM Notification WHERE taskId = :taskId")
    suspend fun getNotificationByTaskId(taskId: Int): NotificationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationEntity)

    @Delete(entity = NotificationEntity::class)
    suspend fun deleteNotification(notificationId: Int)
}