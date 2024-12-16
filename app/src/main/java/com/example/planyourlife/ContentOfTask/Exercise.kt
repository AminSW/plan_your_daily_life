package com.example.planyourlife.ContentOfTask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.planyourlife.Tasks.TaskItem
import com.example.planyourlife.Tasks.TaskOfPlan
import com.example.planyourlife.ViewOfTask.CheckBoxItemWithNumber
import com.example.planyourlife.ViewOfTask.CheckBoxItemWithTime
import com.example.planyourlife.ViewOfTask.TaskListScreen


class Workout(
    val exerciseList: MutableList<TaskItem>, id: Int, name: String
): TaskOfPlan(id, name)
{

    @Composable
    override fun ShowTaskContent()
    {
        TaskListScreen(name = "Workout list", taskList = exerciseList)
    }

}

class Exercise(val name: String, private val number: Int, private val exerciseWithTime: Boolean, isCompleted: Boolean) : TaskItem() {

    private var isCompleted by mutableStateOf(isCompleted)
        private set

    private fun updateCompletionStatus() {
        isCompleted = !isCompleted
    }

    @Composable
    override fun showDetails() {
        if(exerciseWithTime)
        {
            CheckBoxItemWithTime(
                name = name,
                number = number,
                isChecked = isCompleted)
            {
                    updateCompletionStatus() // Durumu burada güncelleriz
            }
        }
        else
        {
            CheckBoxItemWithNumber(
                name = name,
                number = number,
                isChecked = isCompleted)
            {
                updateCompletionStatus() // Durumu burada güncelleriz
            }
        }
    }
}

