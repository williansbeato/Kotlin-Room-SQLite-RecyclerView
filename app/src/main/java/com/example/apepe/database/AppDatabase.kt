package com.example.apepe.database

import android.app.Person
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apepe.database.dao.TaskDao

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao():TaskDao

}