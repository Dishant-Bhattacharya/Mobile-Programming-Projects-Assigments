package com.example.tudu

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tudu.R

class MainActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var addTaskButton: Button
    private lateinit var taskListView: ListView
    private lateinit var taskAdapter: ArrayAdapter<String>
    private val taskList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.taskInput)
        prioritySpinner = findViewById(R.id.prioritySpinner)
        addTaskButton = findViewById(R.id.addTaskButton)
        taskListView = findViewById(R.id.taskListView)

        // Setting up Spinner
        val priorities = arrayOf("Low priority", "Medium priority", "High priority")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = spinnerAdapter

        // Setting up ListView
        taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        taskListView.adapter = taskAdapter

        //Adding Listeners
        addTaskButton.setOnClickListener {
            val task = taskInput.text.toString()
            val priority = prioritySpinner.selectedItem.toString()
            if (task.isNotEmpty()) {
                taskList.add("$task - Priority: $priority")
                taskAdapter.notifyDataSetChanged()
                taskInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Using Handle case here for when nothing is selected
            }
        }
    }
}
