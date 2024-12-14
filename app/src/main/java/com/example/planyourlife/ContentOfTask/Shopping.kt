package com.example.planyourlife.ContentOfTask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.planyourlife.Tasks.TaskItem
import com.example.planyourlife.ViewOfTask.CheckBoxItemWithNumber
import com.example.planyourlife.ViewOfTask.TaskListScreen

@Composable
fun ShoppingTableScreen() {
    val shoppingList = remember {
        mutableStateListOf(
            ShoppingItem("product 1", 2, false) as TaskItem,
            ShoppingItem("product 2", 2, false) as TaskItem,
            ShoppingItem("product 3", 2, false) as TaskItem,
            ShoppingItem("product 4", 2, false) as TaskItem,
        )
    }
    val shopping = Shopping(shoppingList = shoppingList, id = 1, name = "shopping")
    shopping.ShowTaskContent()
}

class Shopping(
    private val shoppingList: MutableList<TaskItem>, id: Int, name: String
) {
    @Composable
    fun ShowTaskContent() {
        TaskListScreen(name = "Shopping list", taskList = shoppingList)
    }
}

class ShoppingItem(val name: String, val number: Int, isCompleted: Boolean): TaskItem()
{
    var isCompleted by mutableStateOf(isCompleted)
        private set

    fun updateCompletionStatus() {
        isCompleted = !isCompleted
    }

    @Composable
    override fun showDetails() {
        CheckBoxItemWithNumber(
            name = name,
            number = number,
            isChecked = isCompleted)
        {
            updateCompletionStatus() // Durumu burada güncelleriz
        }
    }
}



