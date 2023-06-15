package com.example.todolistchallenge

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolistchallenge.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class NewTaskSheet(private var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if (taskItem != null) {

            val locale = Locale.getDefault()
            if (locale.language.toString() == Locale("ar").language.toString()) {
                binding.titleOfTask.text = "تعديل علي مهمة"
                binding.createTask.text = "عدل"

            } else {
                binding.titleOfTask.text = "Edit task"
                binding.createTask.text = "Edit"
            }
            binding.removeTask.visibility = View.VISIBLE

            val editable = Editable.Factory.getInstance()
            binding.titleTask.text = editable.newEditable(taskItem!!.title)
            binding.descTask.text = editable.newEditable(taskItem!!.desc)
        } else {
            if (Locale.getDefault().language.toString() != Locale("ar").language.toString())
                binding.titleOfTask.text = "New Task"
            else
                binding.titleOfTask.text = "مهمة جديد؟"
            binding.removeTask.visibility = View.GONE

        }

        taskViewModel = ViewModelProvider(activity)[TaskViewModel::class.java]
        binding.createTask.setOnClickListener {
            createTask()
        }
        binding.removeTask.setOnClickListener {
            removeTask()

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
    private fun removeTask() {
        if (taskItem != null) {
            taskViewModel.removeTaskItem(taskItem!!.id)
        } else {
            Log.d("[removeTask]:", "ERROR Here , come check")
        }
        dismiss()
    }

}