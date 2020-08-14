package com.example.apepe.database.dao

import androidx.room.*
import com.example.apepe.model.Task

@Dao
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