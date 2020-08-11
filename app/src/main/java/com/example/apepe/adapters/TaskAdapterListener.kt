package com.example.apepe.adapters

import com.example.apepe.model.Task

interface TaskAdapterListener {
    fun onTaskSelected(task: Task)
}