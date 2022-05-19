package com.kuznetsova.mlanning.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kuznetsova.mlanning.data.TasksRepositoryImplementation
import com.kuznetsova.mlanning.data.room.AppDatabase
import com.kuznetsova.mlanning.data.room.RoomTasksDataSource
import com.kuznetsova.mlanning.domain.TaskPriority
import com.kuznetsova.mlanning.domain.TasksRepository

class MlanningApplication : Application() {

    val tasksRepository: TasksRepository by lazy {
        createTasksRepository()
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    private fun createTasksRepository(): TasksRepository {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tasks-database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL(
                    "INSERT INTO TaskPriority(value) values ${
                        TaskPriority.values().joinToString(separator = ",\n") { "(${it.ordinal})" }
                    };"
                )
            }
        })
            .build()
        return TasksRepositoryImplementation(
            localDataSource = RoomTasksDataSource(db)
        )
    }

    companion object {
        private var _instance: MlanningApplication? = null
        val instance: MlanningApplication get() = requireNotNull(_instance)
    }
}