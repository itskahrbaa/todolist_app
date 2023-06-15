package com.example.todolistchallenge

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolistchallenge.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet(private var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if (taskItem != null) {

            binding.titleOfTask.text = "Edit task"
            binding.createTask.text = "Edit"

            val editable = Editable.Factory.getInstance()
            binding.titleTask.text = editable.newEditable(taskItem!!.title)
            binding.descTask.text = editable.newEditable(taskItem!!.desc)
        } else {
            binding.titleOfTask.text = "New Task"
        }

        taskViewModel = ViewModelProvider(activity)[TaskViewModel::class.java]
        binding.createTask.setOnClickListener {
            createTask()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createTask() {
        val title = binding.titleTask.text.toString()
        val desc = binding.descTask.text.toString()
        if (taskItem == null) {
            val newTask = TaskItem(title, desc)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskViewModel.updateTaskItem(taskItem!!.id, title, desc)
        }
        binding.titleTask.setText("")
        binding.titleTask.setText("")
        dismiss()
    }
}