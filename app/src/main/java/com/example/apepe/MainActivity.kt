package com.example.apepe

import android.content.Intent
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
    private var msgPrincipal = "Terminei o bagulho"
    private var msgSecundaria = "Se liga no role que eu fiz : "



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bancao()
        carregador()

        btAdd.setOnClickListener {
            val task = Task ("", "", false)
            task.id = 0L
            adapter.addTask(task)
        }

        carregador()

    }


    fun bancao (){
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "task-bancao")
            .allowMainThreadQueries()
            .build()
        dao = db.taskDao()


    }

    fun carregador (){

        tasks = dao.getAll().toMutableList()
        adapter = TaskAdapter(tasks.toMutableList(), this, applicationContext)
        rvLista.adapter = adapter
        rvLista.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

//    override fun onTaskSelected(task: Task) {
//    }

    override fun taskSalvar(task: Task) {
        dao.insert(task)
        carregador()
    }

    override fun taskExcluir(task: Task) {
        dao.delete(task)
        carregador()
    }

    override fun taskEditar(task: Task) {
        dao.update(task)
        carregador()
    }

    override fun taskCompart(task: Task) {
        val compartilha = Intent(Intent.ACTION_SEND)

        with(compartilha){
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, msgPrincipal)
            putExtra(Intent.EXTRA_TEXT, msgSecundaria+task.title)
        }
        startActivity(compartilha)
    }


}