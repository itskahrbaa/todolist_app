package com.example.todolistchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TaskViewModel: ViewModel()
{
    var taskitem = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskitem.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem) {
        val list = taskitem.value
        list?.add(newTask)
        taskitem.postValue(list)
    }

    fun updateTaskItem(id: UUID, title: String, desc: String) {
        val list = taskitem.value
        val task = list!!.find { it.id == id }!!
        task.title = title
        task.desc = desc
        taskitem.postValue(list)
    }

    fun removeTaskItem(id: UUID) {
        var list = taskitem.value
        val task = list!!.find { it.id == id }!!
        list.remove(task)
        taskitem.postValue(list)
    }
}