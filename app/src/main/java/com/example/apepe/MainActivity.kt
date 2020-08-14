package com.example.apepe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.apepe.adapters.TaskAdapter
import com.example.apepe.adapters.TaskAdapterListener
import com.example.apepe.database.AppDatabase
import com.example.apepe.database.dao.TaskDao
import com.example.apepe.model.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskAdapterListener {

    private lateinit var adapter: TaskAdapter
    private lateinit var dao: TaskDao
    private lateinit var tasks : MutableList<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "task-bancao")
            .allowMainThreadQueries()
            .build()
        dao = db.taskDao()
        tasks = dao.getAll().toMutableList()


        adapter = TaskAdapter(tasks.toMutableList(), this, applicationContext)
        rvLista.adapter = adapter
        rvLista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        btAdd.setOnClickListener {

        }
    }

    override fun onTaskSelected(task: Task) {
    }


}