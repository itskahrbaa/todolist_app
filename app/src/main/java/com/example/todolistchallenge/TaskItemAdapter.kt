package com.example.todolistchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(private val taskList: List<TaskItem>, private val clickListener: TaskItemClickListener): RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = taskList[position].title
        holder.desc.text = taskList[position].desc
        holder.itemView.setOnClickListener {
            clickListener.editTask(taskList[position])
        }
    }

    override fun getItemCount(): Int = taskList.size

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val title: TextView = itemview.findViewById(R.id.title)
        val desc: TextView = itemview.findViewById(R.id.description)
    }
}
interface TaskItemClickListener {
    fun editTask(taskItem: TaskItem)
}