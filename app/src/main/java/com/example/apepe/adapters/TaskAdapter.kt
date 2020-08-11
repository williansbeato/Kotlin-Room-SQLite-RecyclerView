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

class TaskAdapter(val  listener: TaskAdapterListener, context: Context): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val dao: TaskDao
    private var tasks : MutableList<Task>


    fun save(task: Task): Int{
        return if(task.id == 0L){
            task.id = dao.insert(task)

            val position = 0
            tasks.add(position, task)
            notifyItemInserted(position)
            position
        }else{
            dao.update(task)

            val position = tasks.indexOf(task)
            notifyItemChanged(position)
            position
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun fillView(task: Task){

        }
    }

    init {
       val db = Room.databaseBuilder(context, AppDatabase::class.java, "task-bancao")
           .allowMainThreadQueries()
           .build()
        dao = db.taskDao()
        tasks = dao.getAll().toMutableList()
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