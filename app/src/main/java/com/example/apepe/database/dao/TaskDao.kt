package com.example.apepe.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.apepe.model.Task

interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task): Long

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}