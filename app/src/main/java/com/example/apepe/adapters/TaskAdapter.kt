package com.example.apepe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.apepe.database.AppDatabase
import com.example.apepe.database.dao.TaskDao
import com.example.apepe.model.Task

class TaskAdapter(var tasks: MutableList<Task>, val listener: TaskAdapterListener, context: Context): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun fillView(task: Task){

        }
    }

    fun addTask(task: Task) {
        tasks.add(0, task)
        notifyItemInserted(0)

        return

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      ViewHolder(
          LayoutInflater
              .from(parent.context)
              .inflate(viewType, parent, false)
      )



    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
       val task= tasks[position]
        holder.fillView(task)
    }


}